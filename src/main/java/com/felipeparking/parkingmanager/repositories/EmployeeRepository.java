package com.felipeparking.parkingmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeparking.parkingmanager.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
List<Employee> findByActiveTrue();
}
