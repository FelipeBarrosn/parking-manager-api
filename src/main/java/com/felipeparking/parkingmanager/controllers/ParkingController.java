package com.felipeparking.parkingmanager.controllers;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipeparking.parkingmanager.dto.CreateParkingDto;
import com.felipeparking.parkingmanager.dto.DetailsParkingDto;
import com.felipeparking.parkingmanager.dto.ListParkingDto;
import com.felipeparking.parkingmanager.dto.UpdateParkingDto;
import com.felipeparking.parkingmanager.entities.Parking;
import com.felipeparking.parkingmanager.services.ParkingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/parkings")
public class ParkingController {

	private final ParkingService parkingService;

	public ParkingController(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Parking insert(@Valid @RequestBody CreateParkingDto createparkingdto) {
		return parkingService.insert(createparkingdto);

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
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		parkingService.softDelete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Parking> update(@PathVariable Long id, @Valid @RequestBody UpdateParkingDto updateparkingdto) {
		return ResponseEntity.ok(parkingService.update(id, updateparkingdto));

	}

}
