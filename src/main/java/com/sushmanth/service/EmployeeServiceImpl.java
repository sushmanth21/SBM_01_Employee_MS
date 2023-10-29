package com.sushmanth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushmanth.entity.EmployeeDTO;
import com.sushmanth.feign.AddressFeignUtil;
import com.sushmanth.model.Employee;
import com.sushmanth.repositary.EmployeeRepositary;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepositary employeeRepositary;
	
	@Autowired
	private AddressFeignUtil addressFeignClient;

	@Override
	public Employee getEmployeeById(int id) {
		Optional<EmployeeDTO> employeeDtoOpt = employeeRepositary.findById(id);

		Employee employee = null;

		if (!employeeDtoOpt.isEmpty()) {
			employee = new Employee();
					
			employee.setEmployeeId(employeeDtoOpt.get().getEmployeeId());
			employee.setEmployeeName(employeeDtoOpt.get().getEmployeeName());
			employee.setAddress(employeeDtoOpt.get().getAddress());
			
			com.sushmanth.model.Address address = addressFeignClient.getAddressById(employee.getAddress());
			employee.setAddressData(address);;
		}
		
		return employee;
	}

	@Override
	public Employee addEmployee(Employee employee) {

		EmployeeDTO employeeDto = new EmployeeDTO();
		Employee employeeRes = new Employee();

		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setEmployeeName(employee.getEmployeeName());
		employeeDto.setAddress(employee.getAddress());
		
		EmployeeDTO result = employeeRepositary.save(employeeDto);
		
		employeeRes.setEmployeeId(result.getEmployeeId());
		employeeRes.setEmployeeName(result.getEmployeeName());
		employeeRes.setAddress(result.getAddress());
		
		return employeeRes;
	}
	
	@Override
	public Employee deleteEmployeeById(int id) {
		Optional<EmployeeDTO> employeeData = employeeRepositary.findById(id);
		
		Employee employee = null; 
		if(!employeeData.isEmpty()) {
			employee = new Employee();
			
			employeeRepositary.deleteById(id);
			
			employee.setEmployeeId(employeeData.get().getEmployeeId());
			employee.setEmployeeName(employeeData.get().getEmployeeName());
			employee.setAddress(employeeData.get().getAddress());
		}
		return employee;
	}

}
