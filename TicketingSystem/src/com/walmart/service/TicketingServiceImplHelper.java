package com.walmart.service;

import java.util.ArrayList;
import java.util.List;

import com.walmart.constants.Constants;
import com.walmart.model.Customer;
import com.walmart.model.FindAndHoldRequest;
import com.walmart.model.FindAndHoldResponse;
import com.walmart.model.FindAvailableResponse;
import com.walmart.model.ReserveAndCommitRequest;
import com.walmart.model.ReserveAndCommitResponse;
import com.walmart.model.Seat;
import com.walmart.model.Stage;

public class TicketingServiceImplHelper {

	HoldTimer held = new HoldTimer();

	Stage stage = Stage.getInstance();
	
	/**
	 * This method finds available sets at customer specified row if it finds
	 * required seats in same row it will holds the seats. in-case if it is
	 * unable to find in same row it will find nearest row and returns the best
	 * available seats to the customer. Customer has to call the same method
	 * with confirmation. if customer doesn't specify the row it will find best
	 * available sets and holds on behalf of customer.
	 * */

	public FindAndHoldResponse findAndHoldSeats(FindAndHoldRequest request) {

		FindAndHoldResponse response = new FindAndHoldResponse();
		
		if(request.isUserConfirm()){
			
			return holdSeats(request);
		}
		
		response.setCustmore(request.getCustomer());

		Character custPreferdRow = request.getCustPreferredRow();

		List<Seat> availableSeats = new ArrayList<Seat>();

		int reqNumbOfSeats = request.getReqNumbOfSeats();
		
		if (reqNumbOfSeats > Stage.seatsAvailable.get()) {

			response.setStatus(FindAndHoldResponse.Status.SeatsNotAvilable);
			response.setMesaage("Currently, " + Stage.seatsAvailable.get() + " seats are available so couldn’t book "
					+ reqNumbOfSeats + " tickets ");
		}

		if (null != custPreferdRow) {

			availableSeats.addAll(findAvailableSeatsInARow(custPreferdRow, reqNumbOfSeats));

			if (availableSeats.size() == reqNumbOfSeats) {
				if (holdAvilableSeats(availableSeats, request.getCustomer())) {
					response.setStatus(FindAndHoldResponse.Status.Sucess);
					response.setMesaage("Requested " + reqNumbOfSeats + " seats are in Hold for "
							+ Constants.HOLD_TIME / 1000 + " seconds please reserve before hold time");
					response.setSeatsHeld(availableSeats);
					response.setTotalAvailableSeats(Stage.seatsAvailable.get());

					return response;
				} else {
					response.setStatus(FindAndHoldResponse.Status.Eror);
					response.setMesaage("Error during Hold process please retry again");
					response.setTotalAvailableSeats(Stage.seatsAvailable.get());
					return response;
				}

			} else {

				availableSeats.addAll(
						findAvailableSeatsInNearestRows(custPreferdRow, reqNumbOfSeats - availableSeats.size()));
				
				response.setBestAvailableSeats(availableSeats);

				if (availableSeats.size() == reqNumbOfSeats) {
					response.setStatus(FindAndHoldResponse.Status.NeedConfirm);
					response.setMesaage("Requested " + reqNumbOfSeats
							+ " seats are not availabel in same row please review available seats and confirm Y/N");
					response.setTotalAvailableSeats(Stage.seatsAvailable.get());
					return response;
				}

				if (availableSeats.size() < reqNumbOfSeats) {
					response.setStatus(FindAndHoldResponse.Status.SeatsNotAvilable);
					response.setMesaage("Currently, " + Stage.seatsAvailable.get()
							+ " seats are available so couldn’t book " + reqNumbOfSeats + " tickets ");
					response.setTotalAvailableSeats(Stage.seatsAvailable.get());
					return response;
				}

			}

		} else {

			availableSeats.addAll(findAvailableSeatsInTheStage(reqNumbOfSeats));

			if (availableSeats.size() == reqNumbOfSeats) {

				if (holdAvilableSeats(availableSeats, request.getCustomer())) {
					response.setStatus(FindAndHoldResponse.Status.Sucess);
					response.setMesaage("Requested " + reqNumbOfSeats + " seats are in Hold for "
							+ Constants.HOLD_TIME / 1000 + "seconds please reserve before hold time");
					response.setSeatsHeld(availableSeats);
					response.setTotalAvailableSeats(Stage.seatsAvailable.get());
					return response;
				} else {
					response.setStatus(FindAndHoldResponse.Status.Eror);
					response.setMesaage("Error during Hold process please retry again");
					response.setTotalAvailableSeats(Stage.seatsAvailable.get());
					return response;
				}
			}

			if (availableSeats.size() < reqNumbOfSeats) {

				response.setBestAvailableSeats(availableSeats);
				response.setStatus(FindAndHoldResponse.Status.SeatsNotAvilable);
				response.setMesaage("Currently, " + Stage.seatsAvailable.get() + " seats are available so couldn’t book "
						+ reqNumbOfSeats + " tickets ");
				response.setTotalAvailableSeats(Stage.seatsAvailable.get());
				return response;
			}
		}

		return response;

	}

	/*
	 * This method takes the customer preferred seats with row and column and
	 * holds if they are available.
	 */
	public FindAndHoldResponse holdSeats(FindAndHoldRequest request) {

		List<Seat> avilableSeats = request.getCustPreferredSeats();
		Customer customer = request.getCustomer();
		FindAndHoldResponse response = new FindAndHoldResponse();

		if (holdAvilableSeats(avilableSeats, customer)) {
			response.setStatus(FindAndHoldResponse.Status.Sucess);
			response.setMesaage("Requested " + avilableSeats.size() + " tickets are in Hold for "
					+ Constants.HOLD_TIME / 1000 + "seconds please resorve before hold time");
			response.setSeatsHeld(avilableSeats);
			response.setTotalAvailableSeats(Stage.seatsAvailable.get());
			return response;

		} else {
			response.setStatus(FindAndHoldResponse.Status.Eror);
			response.setMesaage("Requested Seats are not avialable");
			response.setTotalAvailableSeats(Stage.seatsAvailable.get());
			return response;
		}

	}

	public boolean holdAvilableSeats(List<Seat> avilableSeats, Customer customer) {

		List<Seat> heldSeats = new ArrayList<Seat>();

		for (Seat seat : avilableSeats) {

			String key = seat.getRowNo().toString() + seat.getColNo();

			Seat st = stage.getSeatMap().get(key);

			if (st.isAvailable()) {
				st.setState(Seat.State.H);
				st.setCustomer(customer);
				heldSeats.add(st);
				Stage.seatsAvailable.getAndDecrement();
				Stage.seatsHeld.getAndIncrement();
				stage.getSeatMap().put(key, st);

			}

		}

		if (avilableSeats.size() != heldSeats.size()) {

			if (heldSeats.size() > 0)
				releaseHeldSeats(heldSeats);

			return false;
		}

		held.startHoldTimer(heldSeats);
		return true;

	}

	private void releaseHeldSeats(List<Seat> heldSeats) {

		for (Seat st : heldSeats) {

			String key = st.getRowNo().toString() + st.getColNo();

			st = stage.getSeatMap().get(key);

			if (st.isHeld()) {

				st.setState(Seat.State.A);
				st.setCustomer(null);
				Stage.seatsAvailable.getAndIncrement();
				Stage.seatsHeld.getAndDecrement();
				st = stage.getSeatMap().put(key, st);

			}

		}

	}

	private void releaseReservedSeats(List<Seat> reservedSeats) {

		for (Seat st : reservedSeats) {

			String key = st.getRowNo().toString() + st.getColNo();

			st = stage.getSeatMap().get(key);

			if (st.isReserved()) {

				st.setState(Seat.State.A);
				st.setCustomer(null);
				Stage.seatsAvailable.getAndIncrement();
				Stage.seatsReserved.getAndDecrement();
				st = stage.getSeatMap().put(key, st);

			}

		}

	}
	
	/**
	 * This method reserves customer held seats if they are still under hold
	 * time.
	 */

	public ReserveAndCommitResponse reserveAndCommit(ReserveAndCommitRequest request) {

		List<Seat> heldSeats = request.getSeatsHeld();
		List<Seat> reservedList = null;
		Customer cust = request.getCustmore();
		ReserveAndCommitResponse response = new ReserveAndCommitResponse();
		response.setCustomer(request.getCustmore());

		reservedList = reserveAndCommit(heldSeats, cust);

		if (heldSeats.size() == reservedList.size()) {
			response.setStatus(ReserveAndCommitResponse.Status.Sucess);
			response.setMessage(" Requested " + heldSeats.size() + " seats are reserved");
		} else {
			response.setStatus(ReserveAndCommitResponse.Status.Eror);
			response.setMessage("Seats hold time passed. Please retry booking");

		}

		response.setSeatsReserved(reservedList);
		return response;

	}

	public List<Seat> reserveAndCommit(List<Seat> heldSeats, Customer customer) {

		List<Seat> reservedList = new ArrayList<Seat>();

		for (Seat st : heldSeats) {

			String key = st.getRowNo().toString() + st.getColNo();

			st = stage.getSeatMap().get(key);

			if (st.isHeld()) {
				if (null != st.getCustomer()
						&& st.getCustomer().getCustomerName().equals(customer.getCustomerName())
						&& st.getCustomer().getCustomerPhNo().equals(customer.getCustomerPhNo())) {

					st.setState(Seat.State.R);
					Stage.seatsReserved.getAndIncrement();
					Stage.seatsHeld.getAndDecrement();
					reservedList.add(st);
					st = stage.getSeatMap().put(key, st);
				}

			}

		}

		if (heldSeats.size() != reservedList.size()) {

			if (reservedList.size() > 0) {
				releaseReservedSeats(reservedList);
				reservedList.clear();
			}

		}

		return reservedList;

	}

	public List<Seat> findAvailableSeatsInARow(Character preferredRow, int reqNumSeats) {
		int count = 0;

		List<Seat> availableSeats = new ArrayList<Seat>();

		for (int col = 1; col <= stage.getNoColumns(); col++) {

			String key = preferredRow.toString() + col;
			Seat st = stage.getSeatMap().get(key);

			if (st.isAvailable()) {

				availableSeats.add(st);
				count++;
				if (count == reqNumSeats)
					return availableSeats;
			}

		}

		return availableSeats;
	}

	public List<Seat> findAvailableSeatsInNearestRows(Character preferredRow, int reqNumSeats) {
		int count = 0;

		List<Seat> availableSeats = new ArrayList<Seat>();

		// Check for Available Seats in the back rows.
		for (Character row = (char) (preferredRow+1); row <= stage.getNoOfRow(); row++) {

			for (int col = 1; col <= stage.getNoColumns(); col++) {

				String key = row.toString() + col;
				Seat st = stage.getSeatMap().get(key);

				if (st.isAvailable()) {

					availableSeats.add(st);
					count++;
					if (count == reqNumSeats)
						return availableSeats;
				}

			}

		}

		// Check for Available Seats in the front rows.
		for (Character row = (char) (preferredRow-1); row >= 'A'; row--) {
			for (int col = 1; col <= stage.getNoColumns(); col++) {

				String key = row.toString() + col;
				Seat st = stage.getSeatMap().get(key);

				if (st.isAvailable()) {

					availableSeats.add(st);
					count++;
					if (count == reqNumSeats)
						return availableSeats;
				}

			}
		}

		return availableSeats;
	}

	public List<Seat> findAvailableSeatsInTheStage(int reqNumSeats) {

		int count = 0;

		List<Seat> availableSeats = new ArrayList<Seat>();

		for (Character row = 'A'; row <= stage.getNoOfRow(); row++) {

			for (int col = 1; col <= stage.getNoColumns(); col++) {

				String key = row.toString() + col;
				Seat st = stage.getSeatMap().get(key);

				if (st.isAvailable()) {

					availableSeats.add(st);
					count++;
					if (count == reqNumSeats)
						return availableSeats;

				}

			}

		}

		return availableSeats;
	}
	
	/*
	 * This method displays seat map (non-Javadoc)
	 */

	public void displaySeatMap() {

		for (int col = 1; col <= stage.getNoColumns(); col++) {

			System.out.print("\t" + col);

		}

		System.out.println();

		for (Character row = 'A'; row <= stage.getNoOfRow(); row++) {

			System.out.print(row);

			for (int col = 1; col <= stage.getNoColumns(); col++) {

				String key = row.toString() + col;
				Seat st = stage.getSeatMap().get(key);
				System.out.print("\t" + st.getState());

			}

			System.out.println();

		}
		System.out.println("A - Available : " + Stage.seatsAvailable);
		System.out.println("H - Hold : " + Stage.seatsHeld);
		System.out.println("A - Reserved : " + Stage.seatsReserved);
	}

	/*
	 * This method returns number of seats Available,Hold and Reserved.
	 */
	public FindAvailableResponse findNoOfSeatsAvilable() {

		FindAvailableResponse response = new FindAvailableResponse();

		response.setTotalAvailableSeats(Stage.seatsAvailable.get());
		response.setTotalHeldSeats(Stage.seatsHeld.get());
		response.setTotalReservedSeats(Stage.seatsReserved.get());

		return response;
	}

}
