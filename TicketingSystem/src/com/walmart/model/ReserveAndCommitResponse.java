package com.walmart.model;

import java.util.List;

public class ReserveAndCommitResponse {
	
	private Status status;   
	String message;
	Customer customer;
	List<Seat> seatsReserved;

	public enum Status {
	       Sucess, NeedConfirm, SeatsNotAvilable, Eror;
	     };
	     
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Seat> getSeatsReserved() {
		return seatsReserved;
	}
	public void setSeatsReserved(List<Seat> seatsResorved) {
		this.seatsReserved = seatsResorved;
	}

	
	

}
