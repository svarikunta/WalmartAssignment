package com.walmat.model;

public class Customer {
	
	String custmoreName;
	String custmorePhNo;
	
	public Customer(String custName, String custId){
		this.custmoreName=custName;
		this.custmorePhNo=custId;
	}

	public String getCustmoreName() {
		return custmoreName;
	}

	public void setCustmoreName(String custmoreName) {
		this.custmoreName = custmoreName;
	}

	public String getCustmorePhNo() {
		return custmorePhNo;
	}

	public void setCustmorePhNo(String custmoreId) {
		this.custmorePhNo = custmoreId;
	}

}
