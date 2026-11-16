package com.garagepulse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garagepulse.dto.CreateWorkOrderRequestDto;
import com.garagepulse.dto.CreateWorkOrderResponseDto;
import com.garagepulse.dto.GetWorkOrderResponseDto;
import com.garagepulse.dto.UpdateWorkOrderDetailsDto;
import com.garagepulse.exception.VehicleNotFoundException;
import com.garagepulse.exception.WorkOrderNotFoundException;
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

	@GetMapping("/workOrder/{workOrderId}")
	public ResponseEntity<?> getWorkOrderbyId(@PathVariable int workOrderId) {
		GetWorkOrderResponseDto dto = new GetWorkOrderResponseDto();
		try {
			dto = workOrderService.getWorkOrderById(workOrderId);
		} catch (WorkOrderNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/workOrders")
	public ResponseEntity<?> getAllWorkOrders() {
		List<GetWorkOrderResponseDto> workOrderList = new ArrayList<>();
		workOrderList = workOrderService.getAllWorkOrders();
		return new ResponseEntity<>(workOrderList, HttpStatus.OK);

	}

	@GetMapping("/Vehicle/{vehicleId}/workOrders")
	public ResponseEntity<?> getWorkOrdersByVehicleId(@PathVariable int vehicleId) {
		List<GetWorkOrderResponseDto> workOrders = new ArrayList<>();
		try {
			workOrders = workOrderService.getWorkOrdersByVehicleId(vehicleId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(workOrders);
	}

	@PutMapping("/workOrder/{workOrderId}")
	public ResponseEntity<?> updateWorkOrder(@PathVariable int workOrderId,
			@RequestBody UpdateWorkOrderDetailsDto detailsDto) {
		GetWorkOrderResponseDto dto = new GetWorkOrderResponseDto();
		try {
			dto = workOrderService.updateWorkOrder(workOrderId, detailsDto);

		} catch (WorkOrderNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@DeleteMapping("/workOrder/{workOrderId}")
	public ResponseEntity<String> deleteVehicle(@PathVariable int workOrderId) {
		try {
			workOrderService.deleteWorkOrder(workOrderId);
		} catch (WorkOrderNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
