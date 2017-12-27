package com.walmart.model;

public class Customer {
	
	String customerName;
	String customerPhNo;
	
	public Customer(String custName, String custId){
		this.customerName=custName;
		this.customerPhNo=custId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhNo() {
		return customerPhNo;
	}

	public void setCustomerPhNo(String customerPhNo) {
		this.customerPhNo = customerPhNo;
	}

}
