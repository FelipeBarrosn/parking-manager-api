package com.felipeparking.parkingmanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parkings")
@NoArgsConstructor
@Getter
@Setter
public class Parking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active = true;
	private String name;
	private String cnpj;
	private String address;
	private String phone;
	private Long motocyclesCapacity;
	private Long carsCapacity;
	
}
