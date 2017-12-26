package com.walmat.model;

import java.util.List;

public class FindAndHoldRequest {
	
	boolean userConfirm;
	Customer Customer;
	int reqNumbOfSeats;
	Character custPreferredRow;
	List<Seat> custPreferdSeats;
	List<Seat> bestAvailableSeats;
	List<Seat> seatsHeld;
	List<Seat> seatsReserved;
	
	
	public List<Seat> getCustPreferdSeats() {
		return custPreferdSeats;
	}
	public void setCustPreferdSeats(List<Seat> custPreferdSeats) {
		this.custPreferdSeats = custPreferdSeats;
	}
	public boolean isUserConfirm() {
		return userConfirm;
	}
	public void setUserConfirm(boolean userConfirm) {
		this.userConfirm = userConfirm;
	}

	public Customer getCustmore() {
		return Customer;
	}
	public void setCustmore(Customer customer) {
		Customer = customer;
	}
	public int getReqNumbOfSeats() {
		return reqNumbOfSeats;
	}
	public void setReqNumbOfSeats(int reqNumbOfSeats) {
		this.reqNumbOfSeats = reqNumbOfSeats;
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
