package com.walmart.model;

public class Seat {
	
	 

	private Character RowNo;
	private int colNo;
	private Customer customer;
		     
	private State state;  
	
	public enum State {
	       A, H, R;
	     };
	
	
	public State getState() {
		return state;
	}


	public void setState(State status) {
		this.state = status;
	}


	public Seat(Character rowNo, int colNo) {
		super();
		RowNo = rowNo;
		this.colNo = colNo;
		this.customer=null;
		this.state=State.A;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Character getRowNo() {
		return RowNo;
	}
	public void setRowNo(Character rowNo) {
		RowNo = rowNo;
	}
	public int getColNo() {
		return colNo;
	}
	public void setColNo(int colNo) {
		this.colNo = colNo;
	}
	public boolean isHeld() {
		return (this.state.equals(State.H));
	}

	public boolean isAvailable() {
		return (this.state.equals(State.A));
	}
	
	
	public boolean isReserved() {
		return (this.state.equals(State.R));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RowNo == null) ? 0 : RowNo.hashCode());
		result = prime * result + colNo;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (RowNo == null) {
			if (other.RowNo != null)
				return false;
		} else if (!RowNo.equals(other.RowNo))
			return false;
		if (colNo != other.colNo)
			return false;
		return true;
	}

	
}
