package com.bway.springproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.model.Employee;
import com.bway.springproject.model.Product;
import com.bway.springproject.repository.ProductRepository;
import com.bway.springproject.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmployeeRestController {
	
	
	
	@Autowired
	private EmployeeService service;
	
	
	@Autowired
	private ProductRepository prodRepo;

@GetMapping("/api/emp/list")
	public List<Employee> getAll() {
	
	return service.getAllEmployees();
	
}

@GetMapping("/api/emp/{id}")
public Employee getOneEmp(@PathVariable long id) {
	
	return service.getEmployeeById(id);
}

@PostMapping("/api/emp/add")
public String add(@RequestBody Employee emp) {
	service.addEmployee(emp);
	return "added success";
}

@PutMapping("/api/emp/update")
public  String update(@RequestBody Employee emp) {
	service.updateEmployee(emp);
	return "update success";
}


@DeleteMapping("/api/emp/delete/{id}")
public String delete(@PathVariable long id) {
	service.deleteEmployee(id);
	return "delete success";
}


@GetMapping("/api/emp/j2o")
public String jsonToObject() {
	RestTemplate rt = new RestTemplate();
	Employee emp = rt.getForObject("http://localhost:8080/api/emp/2", Employee.class);
	
	return "FirstName =" +emp.getFname() ;
}

@GetMapping("/api/emp/ja2oa")
public String jsonArraytoObjectArray() {
	RestTemplate temp = new RestTemplate();
Employee emps[] = temp.getForObject("http://localhost:8080/api/emp/list", Employee[].class);
	return "Name = " +emps[2].getFname()+ " "+emps[0].getLname();
}

@GetMapping("/api/loadProduct")
public String callRestApi() {
	RestTemplate temp = new RestTemplate();
	Product[] products = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
   prodRepo.saveAll(List.of(products));
	
	return "success";
}



	
}
