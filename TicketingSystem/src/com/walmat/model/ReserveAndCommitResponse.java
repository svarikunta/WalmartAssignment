package com.walmat.model;

import java.util.List;

public class ReserveAndCommitResponse {
	
	private Status status;   
	String mesaage;
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
	public String getMesaage() {
		return mesaage;
	}
	public void setMesaage(String mesaage) {
		this.mesaage = mesaage;
	}

	public Customer getCustmore() {
		return customer;
	}
	public void setCustmore(Customer customer) {
		this.customer = customer;
	}
	public List<Seat> getSeatsReserved() {
		return seatsReserved;
	}
	public void setSeatsReserved(List<Seat> seatsResorved) {
		this.seatsReserved = seatsResorved;
	}

	
	

}
