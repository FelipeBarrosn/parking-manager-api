package com.felipeparking.parkingmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipeparking.parkingmanager.entities.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
List<Parking> findByActiveTrue();
}
