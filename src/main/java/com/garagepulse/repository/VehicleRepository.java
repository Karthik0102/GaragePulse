package com.garagepulse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garagepulse.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	Optional<Vehicle> findVehicleByVin(String Vin);

}
