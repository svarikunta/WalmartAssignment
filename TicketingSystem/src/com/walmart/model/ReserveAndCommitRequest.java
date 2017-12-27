package com.walmart.model;

import java.util.List;

public class ReserveAndCommitRequest {
	
	Customer customer;

	List<Seat> seatsHeld;
	
	public Customer getCustmore() {
		return customer;
	}
	
	public void setCustmore(Customer customer) {
		this.customer = customer;
	}

	public List<Seat> getSeatsHeld() {
		return seatsHeld;
	}
	
	public void setSeatsHeld(List<Seat> seatsHeld) {
		this.seatsHeld = seatsHeld;
	}

	
	
	

}
