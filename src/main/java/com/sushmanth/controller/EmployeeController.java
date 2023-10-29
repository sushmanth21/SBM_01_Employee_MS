package com.sushmanth.controller;

import org.springframework.http.HttpStatus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushmanth.model.Employee;
import com.sushmanth.service.EmployeeService;
import com.sushmanth.service.EmployeeServiceImpl;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
		
		Employee result = employeeService.getEmployeeById(id);
		
		return new ResponseEntity<Employee>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		
		Employee result = employeeService.addEmployee(employee);
		
		return new ResponseEntity<Employee>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
		
		Employee result = employeeService.deleteEmployeeById(id);
		
		return new ResponseEntity<Employee>(result, HttpStatus.OK);
	}
}
