package com.felipeparking.parkingmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateParkingDto(
		@NotBlank(message = "Campo obrigatorio - nome")
		@Size(min = 3, message = "O nome deve ter no mínimo 3 letras")
		String name,
		String cnpj,
		String address,
		String phone,
		Long motocyclesCapacity,
		Long carsCapacity
		){}

