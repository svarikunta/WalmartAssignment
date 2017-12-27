package com.walmart.model;

import com.walmart.model.FindAndHoldResponse.Status;

public class FindAvailableResponse {
	
	private Status status; 
	
	private int totalAvailableSeats;
	private int totalHeldSeats;
	private int totalReservedSeats;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getTotalAvailableSeats() {
		return totalAvailableSeats;
	}
	public void setTotalAvailableSeats(int totalAvailableSeats) {
		this.totalAvailableSeats = totalAvailableSeats;
	}
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

}
