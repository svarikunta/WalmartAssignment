package com.walmart.test;



import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.walmart.model.Customer;
import com.walmart.model.FindAndHoldRequest;
import com.walmart.model.FindAndHoldResponse;
import com.walmart.model.FindAvailableResponse;
import com.walmart.model.ReserveAndCommitRequest;
import com.walmart.model.ReserveAndCommitResponse;
import com.walmart.model.Seat;
import com.walmart.service.TicketingService;
import com.walmart.service.TicketingServiceImpl;


public class TestReserveAndCommit {
	
	TicketingService ts= new TicketingServiceImpl();


	
	@Test
	public void testReserveAndCommit(){
		
		FindAndHoldRequest request= new FindAndHoldRequest();
		
		FindAndHoldResponse response=null;

		FindAvailableResponse before=null;
		FindAvailableResponse after=null;
		
		Customer cust = new Customer("Sam","1479569349");
		request.setCustomer(cust);
		List<Seat> custPreferdSeats= new ArrayList<Seat>();
		Seat st1 = new Seat('B', 9);
		custPreferdSeats.add(st1);
		Seat st2 = new Seat('C', 9);
		custPreferdSeats.add(st2);
		Seat st3 = new Seat('D', 9);
		custPreferdSeats.add(st3);
		Seat st4 = new Seat('E', 9);
		custPreferdSeats.add(st4);
		Seat st5 = new Seat('F', 9);
		custPreferdSeats.add(st5);
		request.setCustPreferredSeats(custPreferdSeats);
		ts.displaySeatMap();
		
		before=ts.findNoOfSeatsAvailable();
		
		response=ts.holdSeats(request);
		
		after=ts.findNoOfSeatsAvailable();
		
		
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), FindAndHoldResponse.Status.Sucess);
		Assert.assertEquals(before.getTotalAvailableSeats()-custPreferdSeats.size(),after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats()+custPreferdSeats.size(),after.getTotalHeldSeats());
		
		
		ts.displaySeatMap();
		
		ReserveAndCommitRequest  reserveAndCommitRequest= new ReserveAndCommitRequest();
		reserveAndCommitRequest.setSeatsHeld(response.getSeatsHeld());
		reserveAndCommitRequest.setCustmore(cust);
		
		before=ts.findNoOfSeatsAvailable();
		
		ReserveAndCommitResponse reserveAndCommitResponse=ts.reserveAndCommitSeats(reserveAndCommitRequest);
		
		after=ts.findNoOfSeatsAvailable();
		
		Assert.assertNotNull(reserveAndCommitResponse);
		Assert.assertEquals(reserveAndCommitResponse.getStatus(), ReserveAndCommitResponse.Status.Sucess);
		
		Assert.assertEquals(before.getTotalAvailableSeats(),after.getTotalAvailableSeats());
		Assert.assertEquals(before.getTotalHeldSeats()-reserveAndCommitRequest.getSeatsHeld().size(),after.getTotalHeldSeats());
		Assert.assertEquals(before.getTotalReservedSeats()+reserveAndCommitRequest.getSeatsHeld().size(),after.getTotalReservedSeats());
		
		ts.displaySeatMap();

	}
	
		
	

}
