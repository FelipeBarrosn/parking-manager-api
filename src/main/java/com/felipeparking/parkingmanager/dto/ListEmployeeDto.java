package com.felipeparking.parkingmanager.dto;

import java.util.Date;

public record ListEmployeeDto(Long id, String name, Date dateOfBirth, String parkingName) {

}
