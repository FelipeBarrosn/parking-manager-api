package com.felipeparking.parkingmanager.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipeparking.parkingmanager.dto.CreateEmployeeDto;
import com.felipeparking.parkingmanager.dto.CreateParkingDto;
import com.felipeparking.parkingmanager.dto.DetailsParkingDto;
import com.felipeparking.parkingmanager.dto.ListParkingDto;
import com.felipeparking.parkingmanager.dto.UpdateEmployeeDto;
import com.felipeparking.parkingmanager.dto.UpdateParkingDto;
import com.felipeparking.parkingmanager.entities.Employee;
import com.felipeparking.parkingmanager.entities.Parking;
import com.felipeparking.parkingmanager.services.EmployeeService;
import com.felipeparking.parkingmanager.services.ParkingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/parking")
public class ParkingController {

    private final EmployeeService employeeService;

	private final ParkingService parkingService;

	public ParkingController(ParkingService parkingService, EmployeeService employeeService) {
		this.parkingService = parkingService;
		this.employeeService = employeeService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Long> insert(@Valid @RequestBody CreateParkingDto createparkingdto) {
		var id = parkingService.insert(createparkingdto);
		return ResponseEntity.created(URI.create("http://localhost:8080/parking/" + id)).body(id);
	}

	@GetMapping
	public ResponseEntity<List<ListParkingDto>> findAll() {
		List<ListParkingDto> list = parkingService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailsParkingDto> details(@PathVariable Long id) {
		return ResponseEntity.ok(parkingService.detail(id));
	}

	@DeleteMapping("/{id}/forced-delete")
	public ResponseEntity forcedDelete(@PathVariable Long id) {
		parkingService.forcedDelete(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		parkingService.softDelete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Parking> update(@PathVariable Long id,
			@Valid @RequestBody UpdateParkingDto updateparkingdto) {
		return ResponseEntity.ok(parkingService.update(id, updateparkingdto));

	}

}
