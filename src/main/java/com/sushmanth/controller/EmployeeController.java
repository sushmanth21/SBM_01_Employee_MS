package com.sushmanth.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushmanth.exception.EmployeeException;
import com.sushmanth.model.Employee;
import com.sushmanth.service.EmployeeServiceImpl;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping(value = "/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) throws EmployeeException {

		String result = employeeService.addEmployee(employee);

		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editEmployee/{id}")
	public ResponseEntity<String> editEmployee(@RequestBody Employee employee, @PathVariable int id) throws EmployeeException {

		String result = employeeService.updateEmployeeById(employee, id);

		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) throws EmployeeException {

		String result = employeeService.deleteEmployeeById(id);

		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws EmployeeException {

		Employee result = employeeService.getEmployeeById(id);

		return new ResponseEntity<Employee>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeException {

		List<Employee> result = employeeService.getAllEmployees();

		return new ResponseEntity<List<Employee>>(result, HttpStatus.OK);
	}

}
