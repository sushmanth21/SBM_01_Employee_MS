package com.sushmanth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sushmanth.entity.EmployeeDTO;
import com.sushmanth.exception.EmployeeException;
import com.sushmanth.feign.AddressFeignUtil;
import com.sushmanth.model.Employee;
import com.sushmanth.repositary.EmployeeRepositary;

@Service
@PropertySource("classpath:messages.properties")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepositary employeeRepositary;

	@Autowired
	private Environment environment;

	@Autowired
	private AddressFeignUtil addressFeignClient;

	@Override
	public String addEmployee(Employee employee) throws EmployeeException {

		Optional<EmployeeDTO> employeeData = employeeRepositary.findById(employee.getEmployeeId());

		if (employeeData.isPresent()) {

			String errorMessage = environment.getProperty("Service.EMPLOYEE_ALREADY_EXISTS");

			throw new EmployeeException(errorMessage);

		} else {
			EmployeeDTO employeeDto = new EmployeeDTO();
			EmployeeDTO result = null;

			employeeDto.setEmployeeId(employee.getEmployeeId());
			employeeDto.setEmployeeName(employee.getEmployeeName());
			employeeDto.setAddress(employee.getAddress());

			result = employeeRepositary.save(employeeDto);

			if (result == null) {
				String errorMessage = environment.getProperty("Service.EMPLOYEE_NOT_ADDED");

				throw new EmployeeException(errorMessage);
			}
		}
		return environment.getProperty("Service.EMPLOYEE_ADDED");

	}

	@Override
	public String updateEmployeeById(Employee employee, int id) throws EmployeeException {

		Optional<EmployeeDTO> employeeData = employeeRepositary.findById(id);

		if (employeeData.isEmpty()) {
			String errorMessage = environment.getProperty("Service.EMPLOYEE_NOT_FOUND");

			throw new EmployeeException(errorMessage);
		} else {
			EmployeeDTO employeeDto = employeeData.get();

			employeeDto.setEmployeeId(employee.getEmployeeId());
			employeeDto.setEmployeeName(employee.getEmployeeName());
			employeeDto.setAddress(employee.getAddress());

			EmployeeDTO result = employeeRepositary.save(employeeDto);
			
			if (result == null) {
				String errorMessage = environment.getProperty("Service.EMPLOYEE_NOT_UPDATED");

				throw new EmployeeException(errorMessage);
			}
		}
		return environment.getProperty("Service.EMPLOYEE_UPDATED");

	}

	@Override
	public String deleteEmployeeById(int id) throws EmployeeException {
		Optional<EmployeeDTO> employeeData = employeeRepositary.findById(id);

		if (employeeData.isEmpty()) {
			String errorMessage = environment.getProperty("Service.EMPLOYEE_NOT_FOUND");

			throw new EmployeeException(errorMessage);
		} else {
			employeeRepositary.deleteById(id);
			
			Optional<EmployeeDTO> employeeDel = employeeRepositary.findById(id);

			if(!employeeDel.isEmpty()) {
				String errorMessage = environment.getProperty("Service.EMPLOYEE_NOT_DELETED");
				
				throw new EmployeeException(errorMessage);
			}
		}
		return environment.getProperty("Service.EMPLOYEE_DELETED");

	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeException {
		Optional<EmployeeDTO> employeeDtoOpt = employeeRepositary.findById(id);

		Employee employee = null;

		if (employeeDtoOpt.isEmpty()) {
			String errorMessage = environment.getProperty("Service.EMPLOYEE_NOT_FOUND");

			throw new EmployeeException(errorMessage);

		} else {
			employee = new Employee();

			employee.setEmployeeId(employeeDtoOpt.get().getEmployeeId());
			employee.setEmployeeName(employeeDtoOpt.get().getEmployeeName());
			employee.setAddress(employeeDtoOpt.get().getAddress());

			try {
				com.sushmanth.model.Address address = addressFeignClient.getAddressById(employee.getAddress());
				if (address != null) {
					employee.setAddressData(address);
				}
			} catch (Exception e) {
				String errorMessage = environment.getProperty("Service.ADDRESS_FEIGN_ISSUE");

				throw new EmployeeException(errorMessage);
			}
		}
		return employee;

	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		List<EmployeeDTO> employeeDtoList = employeeRepositary.findAll();

		List<Employee> employeeList = null;

		if (employeeDtoList.isEmpty()) {
			String errorMessage = environment.getProperty("Service.EMPLOYEES_NOT_FOUND");

			throw new EmployeeException(errorMessage);

		} else {
			employeeList = new ArrayList<Employee>();

			for (EmployeeDTO employeeDto : employeeDtoList) {
				Employee employee = new Employee();

				employee.setEmployeeId(employeeDto.getEmployeeId());
				employee.setEmployeeName(employeeDto.getEmployeeName());
				employee.setAddress(employeeDto.getAddress());

				try {
					com.sushmanth.model.Address address = addressFeignClient.getAddressById(employeeDto.getAddress());
					if (address != null) {
						employee.setAddressData(address);
					}
				} catch (Exception e) {
					String errorMessage = environment.getProperty("Service.ADDRESS_FEIGN_ISSUE");

					throw new EmployeeException(errorMessage);
				}
				
				employeeList.add(employee);
			}
		}
		return employeeList;
	}

}
