package com.garagepulse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garagepulse.dto.CreateVehicleDto;
import com.garagepulse.dto.CreateVehicleResponseDto;
import com.garagepulse.dto.GetVehicleResponseDto;
import com.garagepulse.dto.UpdateVehicleRequestDetailsDto;
import com.garagepulse.dto.UpdateVehicleResponseDto;
import com.garagepulse.exception.CustomerNotFoundException;
import com.garagepulse.exception.VehicleAlreadyExistsException;
import com.garagepulse.exception.VehicleNotFoundException;
import com.garagepulse.repository.CustomerRepository;
import com.garagepulse.service.VehicleServices;

@RestController
@RequestMapping("/Api")
public class VehicleController {

	@SuppressWarnings("unused")
	private final CustomerRepository customerRepository;

	private final VehicleServices vehicleService;

	public VehicleController(VehicleServices vehicleServices, CustomerRepository customerRepository) {
		this.vehicleService = vehicleServices;
		this.customerRepository = customerRepository;
	}

	@PostMapping("/vehicle")
	public ResponseEntity<?> createVehicle(@RequestBody CreateVehicleDto createVehicleDto) {
		CreateVehicleResponseDto vehicle = new CreateVehicleResponseDto();
		try {
			vehicle = vehicleService.createVehicle(createVehicleDto);
		} catch (CustomerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
	}

	@GetMapping("/vehicle/{id}")
	public ResponseEntity<?> getVehicleById(@PathVariable int id) {
		GetVehicleResponseDto vehicleById = new GetVehicleResponseDto();
		try {
			vehicleById = vehicleService.getVehicleById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(vehicleById);
	}

	@GetMapping("/vehicles")
	public ResponseEntity<?> getAllVehicles() {
		List<GetVehicleResponseDto> vehicleList = new ArrayList<>();
		vehicleList = vehicleService.getAllVehicles();
		return new ResponseEntity<>(vehicleList, HttpStatus.OK);

	}

	@GetMapping("/Customer/{customerId}/vehicles")
	public ResponseEntity<?> getVehiclesByCustomerId(@PathVariable int customerId) {
		List<GetVehicleResponseDto> vehicles = new ArrayList<>();
		try {
			vehicles = vehicleService.getVehiclesbyCustomerId(customerId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(vehicles);
	}

	@PutMapping("/vehicle/{vehicleId}")
	public ResponseEntity<?> updateVehicleDetails(@PathVariable int vehicleId,
			@RequestBody UpdateVehicleRequestDetailsDto detailsDto) {
		UpdateVehicleResponseDto dto = new UpdateVehicleResponseDto();
		try {
			dto = vehicleService.updateVechile(vehicleId, detailsDto);

		} catch (VehicleNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (CustomerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (VehicleAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/vehicle/{vehicleId}")
	public ResponseEntity<String> deleteVehicle(@PathVariable int vehicleId) {
		try {
			vehicleService.deleteVehicle(vehicleId);
		} catch (VehicleNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
