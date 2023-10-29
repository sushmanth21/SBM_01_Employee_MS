package com.sushmanth.model;

public class Employee {

	private int employeeId;
	private String employeeName;
	private int address;
	
	private Address addressData;
	
	public Address getAddressData() {
		return addressData;
	}
	public void setAddressData(Address addressData) {
		this.addressData = addressData;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	
}
