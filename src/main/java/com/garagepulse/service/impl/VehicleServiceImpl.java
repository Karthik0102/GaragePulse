package com.garagepulse.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garagepulse.dto.CreateVehicleDto;
import com.garagepulse.dto.CreateVehicleResponseDto;
import com.garagepulse.dto.GetVehicleResponseDto;
import com.garagepulse.dto.UpdateVehicleRequestDetailsDto;
import com.garagepulse.dto.UpdateVehicleResponseDto;
import com.garagepulse.entity.Customer;
import com.garagepulse.entity.Vehicle;
import com.garagepulse.exception.CustomerNotFoundException;
import com.garagepulse.exception.VehicleAlreadyExistsException;
import com.garagepulse.exception.VehicleNotFoundException;
import com.garagepulse.repository.CustomerRepository;
import com.garagepulse.repository.VehicleRepository;
import com.garagepulse.service.VehicleServices;

@Service
public class VehicleServiceImpl implements VehicleServices {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private VehicleRepository vehicleRepo;

	public CreateVehicleResponseDto createVehicle(CreateVehicleDto dto) throws Exception {

		Optional<Customer> optCust = customerRepository.findById(dto.getCustomerId());

		if (optCust.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Found!");
		}

		Optional<Vehicle> optVehicle = vehicleRepo.findVehicleByVin(dto.getVin());

		if (optVehicle.isPresent()) {
			throw new Exception("Vehicle already exists!");
		}

		Customer customer = optCust.get();

		Vehicle vehicle = new Vehicle();
		vehicle.setVin(dto.getVin());
		vehicle.setMake(dto.getMake());
		vehicle.setModel(dto.getModel());
		vehicle.setYear(dto.getYear());
		vehicle.setLicensePlate(dto.getLicensePlate());
		vehicle.setCustomer(customer);

		vehicle = vehicleRepo.save(vehicle);

		CreateVehicleResponseDto savedVehicle = new CreateVehicleResponseDto();
		savedVehicle.setId(vehicle.getId());
		savedVehicle.setVin(vehicle.getVin());
		savedVehicle.setMake(vehicle.getMake());
		savedVehicle.setModel(dto.getModel());
		savedVehicle.setYear(dto.getYear());
		savedVehicle.setLicensePlate(dto.getLicensePlate());
		savedVehicle.setCustomerId(customer.getId());

		return savedVehicle;

	}

	public GetVehicleResponseDto getVehicleById(int id) {

		Optional<Vehicle> optVehicle = vehicleRepo.findById(id);

		if (optVehicle.isEmpty()) {
			throw new VehicleNotFoundException("No Vehicle Found");
		}

		Vehicle vehicle = optVehicle.get();

		GetVehicleResponseDto retrivedVehicle = new GetVehicleResponseDto();

		retrivedVehicle.setId(vehicle.getId());
		retrivedVehicle.setVin(vehicle.getVin());
		retrivedVehicle.setMake(vehicle.getMake());
		retrivedVehicle.setModel(vehicle.getModel());
		retrivedVehicle.setYear(vehicle.getYear());
		retrivedVehicle.setLicensePlate(vehicle.getLicensePlate());
		retrivedVehicle.setCustomerId(vehicle.getCustomer().getId());

		return retrivedVehicle;

	}

	public List<GetVehicleResponseDto> getAllVehicles() {

		List<Vehicle> vehicles = vehicleRepo.findAll();
		List<GetVehicleResponseDto> dtoList = new ArrayList<>();

		for (Vehicle vehicle : vehicles) {

			GetVehicleResponseDto dto = new GetVehicleResponseDto();

			dto.setId(vehicle.getId());
			dto.setVin(vehicle.getVin());
			dto.setMake(vehicle.getMake());
			dto.setModel(vehicle.getModel());
			dto.setYear(vehicle.getYear());
			dto.setLicensePlate(vehicle.getLicensePlate());
			dto.setCustomerId(vehicle.getCustomer().getId());

			dtoList.add(dto);
		}

		return dtoList;

	}

	public List<GetVehicleResponseDto> getVehiclesbyCustomerId(int customerId) {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);

		if (optCustomer.isEmpty())
			throw new CustomerNotFoundException("Customer Not Found!");

		List<GetVehicleResponseDto> results = new ArrayList<>();

		Customer customer = optCustomer.get();

		List<Vehicle> vehicleListsByCustomerId = new ArrayList<>();

		vehicleListsByCustomerId = customer.getVehicles();

		for (Vehicle vehicle : vehicleListsByCustomerId) {

			GetVehicleResponseDto dto = new GetVehicleResponseDto();

			dto.setId(vehicle.getId());
			dto.setVin(vehicle.getVin());
			dto.setMake(vehicle.getMake());
			dto.setModel(vehicle.getModel());
			dto.setYear(vehicle.getYear());
			dto.setLicensePlate(vehicle.getLicensePlate());
			dto.setCustomerId(customer.getId());

			results.add(dto);
		}

		return results;
	}

	public UpdateVehicleResponseDto updateVechile(int vehicleId, UpdateVehicleRequestDetailsDto dto) {

		Optional<Vehicle> optVehicle = vehicleRepo.findById(vehicleId);
		Optional<Customer> optCustomer = customerRepository.findById(dto.getCustomerId());
		Optional<Vehicle> optVehicleByVin = vehicleRepo.findVehicleByVin(dto.getVin());

		System.out.println("Customer ID from DTO: " + dto.getCustomerId());

		if (optVehicle.isEmpty()) {
			throw new VehicleNotFoundException("Vehicle Not Found!");
		}
		if (optCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Customer Not Found!");
		}

		if (optVehicleByVin.isPresent() && optVehicleByVin.get().getId() != vehicleId) {
			throw new VehicleAlreadyExistsException("Vin already Exists by another vehicle!");
		}

		Vehicle vehicle = optVehicle.get();
		Customer customer = optCustomer.get();

		vehicle.setVin(dto.getVin());
		vehicle.setMake(dto.getMake());
		vehicle.setModel(dto.getModel());
		vehicle.setYear(dto.getYear());
		vehicle.setLicensePlate(dto.getLicensePlate());
		vehicle.setCustomer(customer);

		vehicleRepo.save(vehicle);

		UpdateVehicleResponseDto updatedVehicle = new UpdateVehicleResponseDto();
		updatedVehicle.setId(vehicleId);
		updatedVehicle.setVin(vehicle.getVin());
		updatedVehicle.setMake(vehicle.getMake());
		updatedVehicle.setModel(vehicle.getModel());
		updatedVehicle.setYear(vehicle.getYear());
		updatedVehicle.setLicensePlate(vehicle.getLicensePlate());
		updatedVehicle.setCustomerId(vehicle.getCustomer().getId());

		return updatedVehicle;

	}

	public String deleteVehicle(int vehicleId) {
		
		System.out.println("vehicle ID :"+vehicleId);
		Optional<Vehicle> optVehicle = vehicleRepo.findById(vehicleId);
		if (optVehicle.isPresent()) {
			Vehicle vehicle = optVehicle.get();
			vehicleRepo.delete(vehicle);
			return " Vehicle Deleted";
		} else {
			throw new VehicleNotFoundException("No Vehicle Found!");
		}
	}
}
