package com.felipeparking.parkingmanager.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateParkingDto(Long id,
		@NotBlank(message = "Campo obrigatório - nome")
		@Size(min = 3, message = "O nome deve ter no mínimo 3 letras")
		String name,
		@NotBlank(message = "Campo obrigatório - cnpj")
		String cnpj,
		@NotBlank(message = "Campo obrigatório - endreço")
		String address,
		@NotBlank(message = "Campo obrigatório - telefone")
		String phone,
		Long motocyclesCapacity,
		Long carsCapacity) {
	

}
