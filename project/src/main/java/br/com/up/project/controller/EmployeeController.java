package br.com.up.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.up.project.model.Employee;
import br.com.up.project.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/insert")
	public void insert(@RequestBody Employee employee) {
		this.employeeService.insert(employee);
	}

	@GetMapping("/find")
	public List<Employee> findAll() {
		return this.employeeService.findAll();
	}

	@GetMapping("/find/employees/should/request/{month}")
	public List<Employee> findShouldRequestEmployeeVacation(@PathVariable(value = "month") int month) {
		return this.employeeService.findShouldRequestEmployeeVacation(month);
	}

}
