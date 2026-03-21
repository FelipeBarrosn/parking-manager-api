package com.felipeparking.parkingmanager.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateParkingDto(Long id,
		@NotBlank(message = "Campo obrigatorio - nome")
		@Size(min = 3, message = "O nome deve ter no mínimo 3 letras")
		String name,
		@NotBlank(message = "Campo obrigatorio - cnpj")
		String cnpj,
		@NotBlank(message = "Campo obrigatorio - endreço")
		String address,
		@NotBlank(message = "Campo obrigatorio - telefone")
		String phone,
		Long motocyclesCapacity,
		Long carsCapacity) {
	

}
