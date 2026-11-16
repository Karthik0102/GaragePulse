package com.garagepulse.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garagepulse.dto.CreateWorkOrderRequestDto;
import com.garagepulse.dto.CreateWorkOrderResponseDto;
import com.garagepulse.entity.Vehicle;
import com.garagepulse.entity.WorkOrder;
import com.garagepulse.exception.VehicleNotFoundException;
import com.garagepulse.repository.VehicleRepository;
import com.garagepulse.repository.WorkOrderRepository;
import com.garagepulse.service.WorkOrderServices;

@Service
public class WorkOrderServiceImplementation implements WorkOrderServices {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private WorkOrderRepository workOrderRepository;

	@Override
	public CreateWorkOrderResponseDto createWorkOrder(CreateWorkOrderRequestDto dto) {

		Optional<Vehicle> optVehicle = vehicleRepository.findById(dto.getVehicleId());

		if (optVehicle.isEmpty()) {
			throw new VehicleNotFoundException("No vehicle Found");
		}

		Vehicle vehicle = optVehicle.get();

		WorkOrder workOrder = new WorkOrder();
		workOrder.setServiceType(dto.getServiceType());
		workOrder.setDescription(dto.getDescription());
		workOrder.setStatus(dto.getStatus());
		workOrder.setServiceDate(LocalDateTime.now());
		workOrder.setEstimatedCost(dto.getEstimatedCost());
		workOrder.setVehicle(vehicle);

		WorkOrder savedOrder = workOrderRepository.save(workOrder);

		CreateWorkOrderResponseDto responseDto = new CreateWorkOrderResponseDto();
		responseDto.setId(savedOrder.getId());
		responseDto.setVehicleId(savedOrder.getVehicle().getId());
		responseDto.setServiceType(savedOrder.getServiceType());
		responseDto.setDescritpion(savedOrder.getDescription());
		responseDto.setStatus(savedOrder.getStatus());
		responseDto.setServiceDate(savedOrder.getServiceDate());
		responseDto.setEstimatedCost(savedOrder.getEstimatedCost());

		System.out.println(savedOrder.getDescription());

		return responseDto;

	}

}
