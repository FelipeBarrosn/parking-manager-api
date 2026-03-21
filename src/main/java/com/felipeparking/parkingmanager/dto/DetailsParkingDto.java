package com.felipeparking.parkingmanager.dto;

public record DetailsParkingDto(
		Long id,
		String name,
		String cnpj,
		String address,
		String phone,
		Long motocyclesCapacity,
		Long carsCapacity
		){}
