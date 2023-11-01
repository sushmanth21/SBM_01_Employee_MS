package com.sushmanth.service;

import java.util.List;

import com.sushmanth.exception.EmployeeException;
import com.sushmanth.model.Employee;

public interface EmployeeService {

	public String addEmployee(Employee employee) throws EmployeeException;
	public String updateEmployeeById(Employee employee, int id) throws EmployeeException;
	public String deleteEmployeeById(int id) throws EmployeeException;
	public Employee getEmployeeById(int id) throws EmployeeException;
	public List<Employee> getAllEmployees() throws EmployeeException;
}
