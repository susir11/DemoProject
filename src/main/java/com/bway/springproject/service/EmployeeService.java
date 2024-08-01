package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Employee;

public interface EmployeeService {
	
	void addEmployee(Employee employee);
	
	void deleteEmployee(Long empId);
	
	void updateEmployee(Employee employee);
	
	Employee getEmployeeById(Long empId);
	
	List<Employee> getAllEmployees();

}
