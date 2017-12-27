package com.walmart.service;

import com.walmart.model.FindAndHoldRequest;
import com.walmart.model.FindAndHoldResponse;
import com.walmart.model.FindAvailableResponse;
import com.walmart.model.ReserveAndCommitRequest;
import com.walmart.model.ReserveAndCommitResponse;

public class TicketingServiceImpl implements TicketingService {

	TicketingServiceImplHelper ticketingServiceImplHelper = new TicketingServiceImplHelper();

	/*
	 * This method returns number of seats Available,Hold and Reserved.
	 */
	@Override
	public FindAvailableResponse findNoOfSeatsAvailable() {

		return ticketingServiceImplHelper.findNoOfSeatsAvilable();
	}

	/**
	 * This method finds available sets at customer specified row if it finds
	 * required seats in same row it will holds the seats. in-case if it is
	 * unable to find in same row it will find nearest row and returns the best
	 * available seats to the customer. Customer has to call the same method
	 * with confirmation. if customer doesn't specify the row it will find best
	 * available sets and holds on behalf of customer.
	 */
	@Override
	public FindAndHoldResponse findAndHoldSeats(FindAndHoldRequest request) {

		return ticketingServiceImplHelper.findAndHoldSeats(request);

	}

	/*
	 * This method takes the customer preferred seats with row and column and
	 * holds if they are not available.
	 */
	@Override
	public FindAndHoldResponse holdSeats(FindAndHoldRequest request) {

		return ticketingServiceImplHelper.holdSeats(request);

	}

	/**
	 * This method reserves customer held seats if they are still under hold
	 * time.
	 */
	@Override
	public ReserveAndCommitResponse reserveAndCommitSeats(ReserveAndCommitRequest request) {

		return ticketingServiceImplHelper.reserveAndCommit(request);

	}

	/*
	 * This method displays seat map (non-Javadoc)
	 * 
	 * @see com.walmart.service.TicketingService#displaySeatMap()
	 */
	public void displaySeatMap() {

		ticketingServiceImplHelper.displaySeatMap();

	}

}
