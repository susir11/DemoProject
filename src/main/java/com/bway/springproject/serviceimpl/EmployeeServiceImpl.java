package com.bway.springproject.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Employee;
import com.bway.springproject.repository.EmployeeRepository;
import com.bway.springproject.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	//calling repository
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public void addEmployee(Employee employee) {
		empRepo.save(employee);
		
	}

	@Override
	public void deleteEmployee(Long empId) {
		empRepo.deleteById(empId);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		empRepo.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(Long empId) {
		
		return empRepo.findById(empId).get();
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return empRepo.findAll();
	}

}
