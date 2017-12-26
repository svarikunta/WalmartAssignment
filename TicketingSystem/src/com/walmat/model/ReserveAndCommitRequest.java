package com.walmat.model;

import java.util.List;

public class ReserveAndCommitRequest {
	
	Customer Customer;

	List<Seat> seatsHeld;
	
	public Customer getCustmore() {
		return Customer;
	}
	
	public void setCustmore(Customer customer) {
		Customer = customer;
	}

	public List<Seat> getSeatsHeld() {
		return seatsHeld;
	}
	
	public void setSeatsHeld(List<Seat> seatsHeld) {
		this.seatsHeld = seatsHeld;
	}

	
	
	

}
