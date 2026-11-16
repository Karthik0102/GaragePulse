package com.garagepulse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garagepulse.dto.CreateWorkOrderRequestDto;
import com.garagepulse.dto.CreateWorkOrderResponseDto;
import com.garagepulse.exception.VehicleNotFoundException;
import com.garagepulse.repository.VehicleRepository;
import com.garagepulse.service.WorkOrderServices;

@RestController
@RequestMapping("/api")
public class WorkOrderServiceController {

	@SuppressWarnings("unused")
	private final VehicleRepository vehicleRepository;

	private WorkOrderServices workOrderService;

	public WorkOrderServiceController(WorkOrderServices workOrderServices, VehicleRepository vehicleRepository) {
		this.workOrderService = workOrderServices;
		this.vehicleRepository = vehicleRepository;
	}

	@PostMapping("/workOrder")
	public ResponseEntity<?> createWorkOrder(@RequestBody CreateWorkOrderRequestDto createWorkOrderRequestDto) {
		CreateWorkOrderResponseDto workOrder = new CreateWorkOrderResponseDto();
		try {
			workOrder = workOrderService.createWorkOrder(createWorkOrderRequestDto);
		} catch (VehicleNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(workOrder);
	}
}
