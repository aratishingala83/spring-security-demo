package com.example.demo.customer;

public class CustomerDTO {
	
	Integer customerId;
	String customerName;
	
	
	public CustomerDTO(Integer customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}
	
	public CustomerDTO() {
		
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Customer Id : "+customerId+"; Customer Name : "+customerName;
	}
	

}
