package com.sushmanth.service;


import com.sushmanth.model.Employee;

public interface EmployeeService {

	public Employee getEmployeeById(int id);
	public Employee addEmployee(Employee employee);
	public Employee deleteEmployeeById(int id);
}
