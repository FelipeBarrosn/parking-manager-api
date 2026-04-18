package com.felipeparking.parkingmanager.dto;

import java.util.Date;

public record DetailsEmployeeDto (Long id, String name, Date dateOfBirth, String password, String email, Boolean active, String parkingName ) {

}
