package com.felipeparking.parkingmanager.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.felipeparking.parkingmanager.dto.CreateEmployeeDto;
import com.felipeparking.parkingmanager.dto.DetailsEmployeeDto;
import com.felipeparking.parkingmanager.dto.ListEmployeeDto;
import com.felipeparking.parkingmanager.dto.UpdateEmployeeDto;
import com.felipeparking.parkingmanager.entities.Employee;
import com.felipeparking.parkingmanager.repositories.EmployeeRepository;
import com.felipeparking.parkingmanager.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
		this.employeeService = employeeService;
		this.employeeRepository = employeeRepository; 
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Long> insertEmplooye(@Valid @RequestBody CreateEmployeeDto createemployeedto) {
		var id = employeeService.insertEmployee(createemployeedto);
		return ResponseEntity.created(URI.create(id.toString())).build();
	}

	@GetMapping
	public ResponseEntity<List<ListEmployeeDto>> findAll(){
		List<ListEmployeeDto> list = employeeService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailsEmployeeDto> details(@PathVariable Long id){
		return ResponseEntity.ok(employeeService.detail(id));
	}
	
	@DeleteMapping("/{id}/forced-delete")
	public ResponseEntity ForcedDelete(@PathVariable Long id) {
		employeeService.ForcedDelete(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}/soft-delete")
	public ResponseEntity SoftDelete(@PathVariable Long id) {
		employeeService.SoftDelete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@Valid @PathVariable Long id, @RequestBody UpdateEmployeeDto updateemployeedto){
		return ResponseEntity.ok(employeeService.updateEmployee(id, updateemployeedto));
	}
	
}
