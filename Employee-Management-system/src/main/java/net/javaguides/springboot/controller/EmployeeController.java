package net.javaguides.springboot.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.employee;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get employee
	
	@GetMapping("employee")
	public List<employee> getAllEmployee(){
		return this.employeeRepository.findAll();
	}
	// Get employee by ID
	@GetMapping("/employee/{id}")
	public ResponseEntity<employee> getEmployeeById(@PathVariable("id") Long employeeId) 
		throws ResourceNotFoundException {
	    employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
	    return ResponseEntity.ok().body(employee);
	}

	// save employee
	@PostMapping("employee")
	public employee createEmployee(@RequestBody employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	// update employee
	@PutMapping("employee/{id}")
	public ResponseEntity<employee> updateEmployee(@PathVariable(value="id") Long employeeId,
			@Valid @RequestBody employee employeeDetails) throws ResourceNotFoundException{
		 employee employee = employeeRepository.findById(employeeId)
		            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
		 employee.setEmail(employeeDetails.getEmail());
		 employee.setFirstName(employeeDetails.getFirstName());
		 employee.setLastName(employeeDetails.getLastName());
		 
		 return ResponseEntity.ok(this.employeeRepository.save(employee));
	}
	
	// delete employee
	@DeleteMapping("employee/{id}")
	public Map<String,Boolean> deleteEmployee(@PathVariable(value="id") Long employeeId) throws ResourceNotFoundException{
		employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
		this.employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap();
		response.put("deleted",Boolean.TRUE);
		
		return response;
		
	}
}
