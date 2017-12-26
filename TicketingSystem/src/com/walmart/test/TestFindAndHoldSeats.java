package com.walmart.test;

import org.junit.Assert;
import org.junit.Test;

import com.walmart.Constats.Constants;
import com.walmart.service.TicketingService;
import com.walmart.service.TicketingServiceImpl;
import com.walmat.model.Customer;
import com.walmat.model.FindAndHoldRequest;
import com.walmat.model.FindAndHoldResponse;
import com.walmat.model.FindAvailableResponse;
import com.walmat.model.ReserveAndCommitRequest;
import com.walmat.model.ReserveAndCommitResponse;

public class TestFindAndHoldSeats {

	TicketingService ts = new TicketingServiceImpl();

	@Test
	public void testHoldSeats() {

		FindAndHoldRequest request = new FindAndHoldRequest();

		FindAndHoldResponse response = null;

		FindAvailableResponse before = null;
		FindAvailableResponse after = null;

		Customer cust = new Customer("charly", "4795693491");
		request.setCustmore(cust);
		request.setReqNumbOfSeats(4);
		request.setCustPreferredRow('C');

		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvilable();

		response = ts.findAndHoldSeats(request);

		after = ts.findNoOfSeatsAvilable();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.Sucess);
		Assert.assertEquals(before.getTotalAvailableSeats() - request.getReqNumbOfSeats(),
				after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats() + request.getReqNumbOfSeats(), after.getTotalHeldSeats());
		// Assert.assertTrue(compareReqestedAndHeldSeats(request.getCustPreferdSeats(),response.getSeatsHeld()));

		ts.displaySeatMap();

		ReserveAndCommitRequest reserveAndCommitRequest = new ReserveAndCommitRequest();
		reserveAndCommitRequest.setSeatsHeld(response.getSeatsHeld());
		reserveAndCommitRequest.setCustmore(cust);

		before = ts.findNoOfSeatsAvilable();

		ReserveAndCommitResponse reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

		after = ts.findNoOfSeatsAvilable();

		Assert.assertNotNull(reserveAndCommitResponse);
		Assert.assertEquals(reserveAndCommitResponse.getStatus(), ReserveAndCommitResponse.Status.Sucess);

		Assert.assertEquals(before.getTotalAvailableSeats(), after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats() - reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalHeldSeats());
		Assert.assertEquals(before.getTotalReservedSeats() + reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalReservedSeats());

		ts.displaySeatMap();

	}

	@Test
	public void testHoldSeatsSplitRow() {

		FindAndHoldRequest request = new FindAndHoldRequest();

		FindAndHoldResponse response = null;

		FindAvailableResponse before = null;
		FindAvailableResponse after = null;

		Customer cust = new Customer("charly", "4795693492");
		request.setCustmore(cust);
		request.setReqNumbOfSeats(8);
		request.setCustPreferredRow('G');

		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvilable();

		response = ts.findAndHoldSeats(request);

		after = ts.findNoOfSeatsAvilable();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.Sucess);
		Assert.assertEquals(before.getTotalAvailableSeats() - request.getReqNumbOfSeats(),
				after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats() + request.getReqNumbOfSeats(), after.getTotalHeldSeats());
		// Assert.assertTrue(compareReqestedAndHeldSeats(request.getCustPreferdSeats(),response.getSeatsHeld()));

		ts.displaySeatMap();

		ReserveAndCommitRequest reserveAndCommitRequest = new ReserveAndCommitRequest();
		reserveAndCommitRequest.setSeatsHeld(response.getSeatsHeld());
		reserveAndCommitRequest.setCustmore(cust);

		before = ts.findNoOfSeatsAvilable();

		ReserveAndCommitResponse reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

		after = ts.findNoOfSeatsAvilable();

		Assert.assertNotNull(reserveAndCommitResponse);
		Assert.assertEquals(reserveAndCommitResponse.getStatus(), ReserveAndCommitResponse.Status.Sucess);

		Assert.assertEquals(before.getTotalAvailableSeats(), after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats() - reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalHeldSeats());
		Assert.assertEquals(before.getTotalReservedSeats() + reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalReservedSeats());

		Customer cust2 = new Customer("David", "3453456639");
		request.setCustmore(cust2);
		request.setReqNumbOfSeats(8);
		request.setCustPreferredRow('G');

		response = ts.findAndHoldSeats(request);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.NeedConfirm);
		request.setCustPreferdSeats(response.getBestAvilableSeats());
		response = ts.holdSeats(request);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.Sucess);

		ts.displaySeatMap();

	}

	@Test
	public void testHoldSeatsBestAvailable() {

		FindAndHoldRequest request = new FindAndHoldRequest();

		FindAndHoldResponse response = null;

		FindAvailableResponse before = null;
		FindAvailableResponse after = null;

		Customer cust = new Customer("name1", "4795669329");
		request.setCustmore(cust);
		request.setReqNumbOfSeats(4);

		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvilable();

		response = ts.findAndHoldSeats(request);

		after = ts.findNoOfSeatsAvilable();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.Sucess);
		Assert.assertEquals(before.getTotalAvailableSeats() - request.getReqNumbOfSeats(),
				after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats() + request.getReqNumbOfSeats(), after.getTotalHeldSeats());
		// Assert.assertTrue(compareReqestedAndHeldSeats(request.getCustPreferdSeats(),response.getSeatsHeld()));

		ts.displaySeatMap();

		ReserveAndCommitRequest reserveAndCommitRequest = new ReserveAndCommitRequest();
		reserveAndCommitRequest.setSeatsHeld(response.getSeatsHeld());
		reserveAndCommitRequest.setCustmore(cust);

		before = ts.findNoOfSeatsAvilable();

		ReserveAndCommitResponse reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

		after = ts.findNoOfSeatsAvilable();

		Assert.assertNotNull(reserveAndCommitResponse);
		Assert.assertEquals(reserveAndCommitResponse.getStatus(), ReserveAndCommitResponse.Status.Sucess);

		Assert.assertEquals(before.getTotalAvailableSeats(), after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats() - reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalHeldSeats());
		Assert.assertEquals(before.getTotalReservedSeats() + reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalReservedSeats());

		ts.displaySeatMap();

	}

	@Test
	public void TestHoldSeatTimer() {

		FindAndHoldRequest request = new FindAndHoldRequest();
		FindAndHoldResponse response = null;

		FindAvailableResponse before = null;
		FindAvailableResponse after = null;

		Customer cust = new Customer("Harry", "2334345678");
		request.setCustmore(cust);
		request.setReqNumbOfSeats(5);
		request.setCustPreferredRow('B');
		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvilable();
		response = ts.findAndHoldSeats(request);
		after = ts.findNoOfSeatsAvilable();

		ts.displaySeatMap();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.Sucess);
		Assert.assertEquals(before.getTotalAvailableSeats(),
				after.getTotalAvailableSeats() + request.getReqNumbOfSeats());
		Assert.assertEquals(before.getTotalHeldSeats() + request.getReqNumbOfSeats(), after.getTotalHeldSeats());

		try {
			Thread.sleep(Constants.HOLD_TIME + 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReserveAndCommitRequest reserveAndCommitRequest = new ReserveAndCommitRequest();
		reserveAndCommitRequest.setSeatsHeld(response.getSeatsHeld());
		reserveAndCommitRequest.setCustmore(cust);
		ReserveAndCommitResponse reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);
		after = ts.findNoOfSeatsAvilable();
		Assert.assertNotNull(reserveAndCommitResponse);
		Assert.assertEquals(before.getTotalAvailableSeats(), after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats(), after.getTotalHeldSeats());
		Assert.assertEquals(before.getTotalReservedSeats(), after.getTotalReservedSeats());
		ts.displaySeatMap();
		// System.out.println(Stage.seatsAvilable.get());
	}

}
