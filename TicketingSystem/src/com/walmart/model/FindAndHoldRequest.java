package com.walmart.model;

import java.util.List;

public class FindAndHoldRequest {
	
	boolean userConfirm;
	Customer customer;
	int reqNoOfSeats;
	Character custPreferredRow;
	List<Seat> custPreferredSeats;
	List<Seat> bestAvailableSeats;
	List<Seat> seatsHeld;
	List<Seat> seatsReserved;
	
	
	public List<Seat> getCustPreferredSeats() {
		return custPreferredSeats;
	}
	public void setCustPreferredSeats(List<Seat> custPreferredSeats) {
		this.custPreferredSeats = custPreferredSeats;
	}
	public boolean isUserConfirm() {
		return userConfirm;
	}
	public void setUserConfirm(boolean userConfirm) {
		this.userConfirm = userConfirm;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getReqNumbOfSeats() {
		return reqNoOfSeats;
	}
	public void setReqNoOfSeats(int reqNoOfSeats) {
		this.reqNoOfSeats = reqNoOfSeats;
	}
	public Character getCustPreferredRow() {
		return custPreferredRow;
	}
	public void setCustPreferredRow(Character custPreferredRow) {
		this.custPreferredRow = custPreferredRow;
	}
	
	public List<Seat> getBestAvailableSeats() {
		return bestAvailableSeats;
	}
	public void setBestAvailableSeats(List<Seat> bestAvilableSeats) {
		this.bestAvailableSeats = bestAvilableSeats;
	}
	public List<Seat> getSeatsHeld() {
		return seatsHeld;
	}
	public void setSeatsHeld(List<Seat> seatsHeld) {
		this.seatsHeld = seatsHeld;
	}
	public List<Seat> getSeatsReserved() {
		return seatsReserved;
	}
	public void setSeatsReserved(List<Seat> seatsResorved) {
		this.seatsReserved = seatsResorved;
	}
	
	
	

}
