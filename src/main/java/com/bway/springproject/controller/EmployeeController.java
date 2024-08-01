package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.DepartmentService;
import com.bway.springproject.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;

	
	@GetMapping("/employee")
	public String getEmployee(Model model) {
		model.addAttribute("dlist", deptService.getAllDepts());			//added to get data of department in employee form
		return "EmployeeForm";
		
	}
	
	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee emp) {  //model attribute to receive data from form
		empService.addEmployee(emp);
		return "redirect:/employee";
	}
	
	@GetMapping("/employeeList")
	public String getAllEmps(Model model) {
		model.addAttribute("emplist", empService.getAllEmployees());
		return "EmployeeListForm";
	}
	
	@GetMapping("/emp/delete")
	public String  delete(@RequestParam long id) {
		empService.deleteEmployee(id);
		return "redirect:/employeeList";
	}
	
	@GetMapping("/emp/edit")
	public String edit(@RequestParam long id, Model model) {
		model.addAttribute("eModel", empService.getEmployeeById(id));
		model.addAttribute("dlist",deptService.getAllDepts());		//to display department list in employee edit form
		return "EmployeeEditForm";
	}
	
	@PostMapping("/emp/update")
	public String update(@ModelAttribute Employee emp) {
		empService.updateEmployee(emp);
		return"redirect:/employeeList";
	}
}
