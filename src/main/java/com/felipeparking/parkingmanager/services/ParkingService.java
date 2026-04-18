package com.felipeparking.parkingmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felipeparking.parkingmanager.dto.CreateEmployeeDto;
import com.felipeparking.parkingmanager.dto.CreateParkingDto;
import com.felipeparking.parkingmanager.dto.DetailsParkingDto;
import com.felipeparking.parkingmanager.dto.ListParkingDto;
import com.felipeparking.parkingmanager.dto.UpdateParkingDto;
import com.felipeparking.parkingmanager.entities.Employee;
import com.felipeparking.parkingmanager.entities.Parking;
import com.felipeparking.parkingmanager.repositories.ParkingRepository;

@Service
public class ParkingService {

	private final ParkingRepository parkingRepository;

	public ParkingService(ParkingRepository repository) {
		this.parkingRepository = repository;
	}

	public Long insert(CreateParkingDto createparkingdto) {
		Parking parking = new Parking();
		parking.setCnpj(createparkingdto.cnpj());
		parking.setName(createparkingdto.name());
		parking.setAddress(createparkingdto.address());
		parking.setPhone(createparkingdto.phone());
		parking.setCarsCapacity(createparkingdto.carsCapacity());
		parking.setMotocyclesCapacity(createparkingdto.motocyclesCapacity());
		parkingRepository.save(parking);
		return parking.getId();
	}

	public List<ListParkingDto> findAll() {
		return parkingRepository.findByActiveTrue().stream()
				.map(inten -> new ListParkingDto(inten.getId(), inten.getName(), inten.getCnpj())).toList();
	}

	public DetailsParkingDto detail(Long id) {
		Parking parking = parkingRepository.findById(id).orElseThrow(() -> new RuntimeException("Parking not found"));
		return new DetailsParkingDto(id, parking.getName(), parking.getCnpj(), parking.getAddress(), parking.getPhone(),
				parking.getMotocyclesCapacity(), parking.getCarsCapacity());
	}

	public void forcedDelete(Long id) {
		parkingRepository.deleteById(id);
	}

	public void softDelete(Long id) {
		Optional<Parking> parking = parkingRepository.findById(id);
		if (parking.isEmpty()) {
			return;
		}
		parking.get().setActive(false);
		parkingRepository.save(parking.get());
	}

	public Parking update(Long id, UpdateParkingDto updateparkingdto) {
		var parking = parkingRepository.findById(id).orElseThrow(() -> new RuntimeException("Parking not found"));
		parking.setName(updateparkingdto.name());
		parking.setCnpj(updateparkingdto.cnpj());
		parking.setAddress(updateparkingdto.address());
		parking.setPhone(updateparkingdto.phone());
		parking.setMotocyclesCapacity(updateparkingdto.motocyclesCapacity());
		parking.setCarsCapacity(updateparkingdto.carsCapacity());
		return parkingRepository.save(parking);
	}

}
