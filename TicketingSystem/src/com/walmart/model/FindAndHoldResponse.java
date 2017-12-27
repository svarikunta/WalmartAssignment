package com.walmart.model;

import java.util.List;

public class FindAndHoldResponse {
	
	private Status status;   
	String message;
	Customer customer;
	Character custPreferredRow;
	Character custPreferredCol;
	List<Seat> seatsHeld;
	List<Seat> seatsReserved;
	List<Seat> bestAvailableSeats;
	
	private int totalAvailableSeats;
	private int totalHeldSeats;
	private int totalReservedSeats;
	
	
	
	public int getTotalHeldSeats() {
		return totalHeldSeats;
	}
	public void setTotalHeldSeats(int totalHeldSeats) {
		this.totalHeldSeats = totalHeldSeats;
	}
	public int getTotalReservedSeats() {
		return totalReservedSeats;
	}
	public void setTotalReservedSeats(int totalReservedSeats) {
		this.totalReservedSeats = totalReservedSeats;
	}
	public int getTotalAvailableSeats() {
		return totalAvailableSeats;
	}
	public void setTotalAvailableSeats(int totalAvailableSeats) {
		this.totalAvailableSeats = totalAvailableSeats;
	}
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
		return message;
	}
	public void setMesaage(String mesaage) {
		this.message = mesaage;
	}

	public Customer getCustmore() {
		return customer;
	}
	public void setCustmore(Customer customer) {
		this.customer = customer;
	}
	public Character getCustPreferredRow() {
		return custPreferredRow;
	}
	public void setCustPreferredRow(Character custPreferredRow) {
		this.custPreferredRow = custPreferredRow;
	}
	public Character getCustPreferredCol() {
		return custPreferredCol;
	}
	public void setCustPreferredCol(Character custPreferredCol) {
		this.custPreferredCol = custPreferredCol;
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
	public void setSeatsReserved(List<Seat> seatsReserved) {
		this.seatsReserved = seatsReserved;
	}
	public List<Seat> getBestAvailableSeats() {
		return bestAvailableSeats;
	}
	public void setBestAvailableSeats(List<Seat> bestAvailableSeats) {
		this.bestAvailableSeats = bestAvailableSeats;
	}
	
	

}
