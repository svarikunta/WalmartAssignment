package com.walmart.service;

import com.walmart.model.FindAndHoldRequest;
import com.walmart.model.FindAndHoldResponse;
import com.walmart.model.FindAvailableResponse;
import com.walmart.model.ReserveAndCommitRequest;
import com.walmart.model.ReserveAndCommitResponse;

public interface TicketingService {

	/*
	 * This method returns number of seats Available, Hold and Reserved.
	 */
	public FindAvailableResponse findNoOfSeatsAvailable();

	/**
	 * This method finds available sets at customer specified row if it finds
	 * required seats in same row it will holds the seats. in-case if it is
	 * unable to find in same row it will find nearest row and returns the best
	 * available seats to the customer. Customer has to call the same method
	 * with confirmation. if customer doesn't specify the row it will find best
	 * available sets and holds on behalf of customer.
	 * 
	 * @param request
	 * @return
	 */
	public FindAndHoldResponse findAndHoldSeats(FindAndHoldRequest request);

	/*
	 * This method takes the customer preferred seats with row and column and
	 * holds if they are not available.
	 */
	public FindAndHoldResponse holdSeats(FindAndHoldRequest request);

	/**
	 * This method reserves customer held seats if they are still under hold
	 * time.
	 * 
	 * @param request
	 * @return
	 */
	public ReserveAndCommitResponse reserveAndCommitSeats(ReserveAndCommitRequest request);

	/**
	 * This method displays seat map.
	 */
	public void displaySeatMap();

}
