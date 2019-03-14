package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	// Add mapping for GET /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null){
			throw new RuntimeException("Employee not found -  " + employeeId);
		}
		return theEmployee;
	}

	// add maping for POST /employees - add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee){

		// also just in case they pass an id in JSON, set id to 0
		// This is to force a save of new item instead of update

		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}

	// Add mapping for PUT /employees
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee){

		employeeService.save(theEmployee);
		return theEmployee;
	}

	// Add mapping for DELETE /employees/{employeeId}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Employee tempEmployee = employeeService.findById(employeeId);
		if(tempEmployee == null){
			throw new RuntimeException("Employee not found -  " + employeeId);
		}
		employeeService.deleteById(employeeId);
		return "deleted employee id : " + employeeId;
	}

}
