package com.garagepulse.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garagepulse.dto.CreateWorkOrderRequestDto;
import com.garagepulse.dto.CreateWorkOrderResponseDto;
import com.garagepulse.dto.GetWorkOrderResponseDto;
import com.garagepulse.dto.UpdateWorkOrderDetailsDto;
import com.garagepulse.entity.Vehicle;
import com.garagepulse.entity.WorkOrder;
import com.garagepulse.exception.VehicleNotFoundException;
import com.garagepulse.exception.WorkOrderNotFoundException;
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

	@Override
	public GetWorkOrderResponseDto getWorkOrderById(int id) {

		Optional<WorkOrder> optWO = workOrderRepository.findById(id);
		if (optWO.isEmpty()) {
			throw new WorkOrderNotFoundException("No Work Order Found!");
		}

		WorkOrder wo = optWO.get();

		GetWorkOrderResponseDto dto = new GetWorkOrderResponseDto();
		dto.setId(wo.getId());
		dto.setVehicleId(wo.getVehicle().getId());
		dto.setServiceType(wo.getServiceType());
		dto.setDescription(wo.getDescription());
		dto.setStatus(wo.getStatus());
		dto.setServiceDate(wo.getServiceDate());
		dto.setEstimatedCost(wo.getEstimatedCost());
		return dto;

	}

	@Override
	public List<GetWorkOrderResponseDto> getAllWorkOrders() {
		List<WorkOrder> workOrders = workOrderRepository.findAll();
		List<GetWorkOrderResponseDto> dtoList = new ArrayList<>();

		for (WorkOrder wo : workOrders) {

			GetWorkOrderResponseDto dto = new GetWorkOrderResponseDto();

			dto.setId(wo.getId());
			dto.setVehicleId(wo.getVehicle().getId());
			dto.setServiceType(wo.getServiceType());
			dto.setDescription(wo.getDescription());
			dto.setStatus(wo.getStatus());
			dto.setServiceDate(wo.getServiceDate());
			dto.setEstimatedCost(wo.getEstimatedCost());

			dtoList.add(dto);
		}

		return dtoList;

	}

	public List<GetWorkOrderResponseDto> getWorkOrdersByVehicleId(int vehicleId) {

		Optional<Vehicle> optVehicle = vehicleRepository.findById(vehicleId);

		if (optVehicle.isEmpty())
			throw new VehicleNotFoundException("Vehicle Not Found!");

		List<GetWorkOrderResponseDto> results = new ArrayList<>();

		Vehicle vehicle = optVehicle.get();

		List<WorkOrder> workOrdersListByVehicleId = new ArrayList<>();

		workOrdersListByVehicleId = vehicle.getWorkOrders();

		for (WorkOrder wo : workOrdersListByVehicleId) {

			GetWorkOrderResponseDto dto = new GetWorkOrderResponseDto();

			dto.setId(wo.getId());
			dto.setVehicleId(wo.getVehicle().getId());
			dto.setServiceType(wo.getServiceType());
			dto.setDescription(wo.getDescription());
			dto.setStatus(wo.getStatus());
			dto.setServiceDate(wo.getServiceDate());
			dto.setEstimatedCost(wo.getEstimatedCost());

			results.add(dto);
		}

		return results;

	}

	public GetWorkOrderResponseDto updateWorkOrder(int workOrderId, UpdateWorkOrderDetailsDto dto) {

		Optional<WorkOrder> optWO = workOrderRepository.findById(workOrderId);

		if (optWO.isEmpty()) {
			throw new WorkOrderNotFoundException("Work Order Not Found!");
		}

		WorkOrder workOrder = optWO.get();
		workOrder.setServiceType(dto.getServiceType());
		workOrder.setDescription(dto.getDescription());
		workOrder.setStatus(dto.getStatus());
		workOrder.setEstimatedCost(dto.getEstimatedCost());

		workOrderRepository.save(workOrder);

		GetWorkOrderResponseDto updatedWorkOrder = new GetWorkOrderResponseDto();
		updatedWorkOrder.setId(workOrderId);
		updatedWorkOrder.setVehicleId(workOrder.getVehicle().getId());
		updatedWorkOrder.setServiceType(workOrder.getServiceType());
		updatedWorkOrder.setDescription(workOrder.getDescription());
		updatedWorkOrder.setStatus(workOrder.getStatus());
		updatedWorkOrder.setEstimatedCost(workOrder.getEstimatedCost());
		updatedWorkOrder.setServiceDate(workOrder.getServiceDate());

		return updatedWorkOrder;

	}

	public String deleteWorkOrder(int workOrderId) {

		Optional<WorkOrder> optWorkOrder = workOrderRepository.findById(workOrderId);
		if (optWorkOrder.isPresent()) {
			WorkOrder workOrder = optWorkOrder.get();
			workOrderRepository.delete(workOrder);
			return " Work Order Deleted";
		} else {
			throw new WorkOrderNotFoundException("No Work Order Found!");
		}
	}
}
