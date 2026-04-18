package com.felipeparking.parkingmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felipeparking.parkingmanager.dto.CreateEmployeeDto;
import com.felipeparking.parkingmanager.dto.DetailsEmployeeDto;
import com.felipeparking.parkingmanager.dto.ListEmployeeDto;
import com.felipeparking.parkingmanager.dto.UpdateEmployeeDto;
import com.felipeparking.parkingmanager.entities.Employee;
import com.felipeparking.parkingmanager.entities.Parking;
import com.felipeparking.parkingmanager.repositories.EmployeeRepository;
import com.felipeparking.parkingmanager.repositories.ParkingRepository;

@Service
public class EmployeeService {

	private final ParkingRepository parkingRepository;

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository, ParkingRepository parkingRepository) {
		this.employeeRepository = employeeRepository;
		this.parkingRepository = parkingRepository;
	}

	public Long insertEmployee(CreateEmployeeDto createemployeedto) {
		Parking parking = parkingRepository.findById(createemployeedto.parkingId())
				.orElseThrow(() -> new RuntimeException("Parking not found."));

		Employee employee = new Employee();
		employee.setName(createemployeedto.name());
		employee.setDateOfBirth(createemployeedto.dateOfBirth());
		employee.setPassword(createemployeedto.password());
		employee.setEmail(createemployeedto.email());

		employee.setParking(parking);
		employeeRepository.save(employee);
		return employee.getId();

	}

	public List<ListEmployeeDto> findAll() {
		return employeeRepository.findAll().stream().map(inten -> new ListEmployeeDto(inten.getId(), inten.getName(),
				inten.getDateOfBirth(), inten.getParking().getName())).toList();

	}

	public DetailsEmployeeDto detail(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found."));

		return new DetailsEmployeeDto(id, employee.getName(), employee.getDateOfBirth(), employee.getPassword(),
				employee.getEmail(), employee.isActive(),employee.getParking().getName());
	}

	public void ForcedDelete(Long id) {
		employeeRepository.deleteById(id);
	}

	public void SoftDelete(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty()) {
			return;
		}
		employee.get().setActive(false);
		employeeRepository.save(employee.get());
	}

	public Employee updateEmployee(Long id, UpdateEmployeeDto updateemployeedto) {

		Parking parking = parkingRepository.findById(updateemployeedto.parkingId())
				.orElseThrow(() -> new RuntimeException(""));

		var employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found."));
		employee.setName(updateemployeedto.name());
		employee.setDateOfBirth(updateemployeedto.dateOfBirth());
		employee.setParking(parking);
		return employeeRepository.save(employee);
	}
}
