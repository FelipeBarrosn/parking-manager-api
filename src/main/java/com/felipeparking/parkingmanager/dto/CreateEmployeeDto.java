package com.felipeparking.parkingmanager.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateEmployeeDto(
		
		Long id,
		@NotBlank(message = "Campo obrigatório - Nome")
		String name,
		@NotNull(message = "Campo obrigatório - Data de Nascimento")
		Date dateOfBirth,
		@NotBlank(message = "Campo obrigatório - Senha")
		@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
		String password,
		@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Formato de e-mail inválido")
		String email,
		Long parkingId
		) {
	
}
