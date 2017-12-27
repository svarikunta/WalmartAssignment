package com.walmart.test;

import org.junit.Assert;
import org.junit.Test;

import com.walmart.constants.Constants;
import com.walmart.model.Customer;
import com.walmart.model.FindAndHoldRequest;
import com.walmart.model.FindAndHoldResponse;
import com.walmart.model.FindAvailableResponse;
import com.walmart.model.ReserveAndCommitRequest;
import com.walmart.model.ReserveAndCommitResponse;
import com.walmart.service.TicketingService;
import com.walmart.service.TicketingServiceImpl;

public class TestFindAndHoldSeats {

	TicketingService ts = new TicketingServiceImpl();

	@Test
	public void testHoldSeats() {

		FindAndHoldRequest request = new FindAndHoldRequest();

		FindAndHoldResponse response = null;

		FindAvailableResponse before = null;
		FindAvailableResponse after = null;

		Customer cust = new Customer("charly", "4795693491");
		request.setCustomer(cust);
		request.setReqNoOfSeats(4);
		request.setCustPreferredRow('C');

		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvailable();

		response = ts.findAndHoldSeats(request);

		after = ts.findNoOfSeatsAvailable();

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

		before = ts.findNoOfSeatsAvailable();

		ReserveAndCommitResponse reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

		after = ts.findNoOfSeatsAvailable();

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
		request.setCustomer(cust);
		request.setReqNoOfSeats(8);
		request.setCustPreferredRow('G');

		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvailable();

		response = ts.findAndHoldSeats(request);

		after = ts.findNoOfSeatsAvailable();

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

		before = ts.findNoOfSeatsAvailable();

		ReserveAndCommitResponse reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

		after = ts.findNoOfSeatsAvailable();

		Assert.assertNotNull(reserveAndCommitResponse);
		Assert.assertEquals(reserveAndCommitResponse.getStatus(), ReserveAndCommitResponse.Status.Sucess);

		Assert.assertEquals(before.getTotalAvailableSeats(), after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats() - reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalHeldSeats());
		Assert.assertEquals(before.getTotalReservedSeats() + reserveAndCommitRequest.getSeatsHeld().size(),
				after.getTotalReservedSeats());

		Customer cust2 = new Customer("David", "3453456639");
		request.setCustomer(cust2);
		request.setReqNoOfSeats(8);
		request.setCustPreferredRow('G');

		response = ts.findAndHoldSeats(request);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.NeedConfirm);
		request.setCustPreferredSeats(response.getBestAvailableSeats());
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
		request.setCustomer(cust);
		request.setReqNoOfSeats(4);

		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvailable();

		response = ts.findAndHoldSeats(request);

		after = ts.findNoOfSeatsAvailable();

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

		before = ts.findNoOfSeatsAvailable();

		ReserveAndCommitResponse reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

		after = ts.findNoOfSeatsAvailable();

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
		request.setCustomer(cust);
		request.setReqNoOfSeats(5);
		request.setCustPreferredRow('B');
		ts.displaySeatMap();

		before = ts.findNoOfSeatsAvailable();
		response = ts.findAndHoldSeats(request);
		after = ts.findNoOfSeatsAvailable();

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
		after = ts.findNoOfSeatsAvailable();
		Assert.assertNotNull(reserveAndCommitResponse);
		Assert.assertEquals(before.getTotalAvailableSeats(), after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats(), after.getTotalHeldSeats());
		Assert.assertEquals(before.getTotalReservedSeats(), after.getTotalReservedSeats());
		ts.displaySeatMap();
		// System.out.println(Stage.seatsAvilable.get());
	}

}
