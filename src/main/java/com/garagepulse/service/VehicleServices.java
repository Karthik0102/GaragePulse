package com.garagepulse.service;

import java.util.List;

import com.garagepulse.dto.CreateVehicleDto;
import com.garagepulse.dto.CreateVehicleResponseDto;
import com.garagepulse.dto.GetVehicleResponseDto;
import com.garagepulse.dto.UpdateVehicleRequestDetailsDto;
import com.garagepulse.dto.UpdateVehicleResponseDto;

public interface VehicleServices {

	CreateVehicleResponseDto createVehicle(CreateVehicleDto dto) throws Exception;

	GetVehicleResponseDto getVehicleById(int id);

	List<GetVehicleResponseDto> getAllVehicles();

	List<GetVehicleResponseDto> getVehiclesbyCustomerId(int customerId);

	UpdateVehicleResponseDto updateVechile(int vehicleId, UpdateVehicleRequestDetailsDto updateVehicleRequestDto);

	String deleteVehicle(int vehicleId);
}
