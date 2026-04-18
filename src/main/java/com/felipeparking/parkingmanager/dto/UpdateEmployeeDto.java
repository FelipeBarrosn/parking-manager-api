package com.felipeparking.parkingmanager.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateEmployeeDto(
		
		@NotBlank(message = "Campo obrigatório - Nome")
		String name,
		@NotNull(message = "Campo obrigatório - Data de Nascimento")
		Date dateOfBirth,
		Long parkingId
		
		) {

}
