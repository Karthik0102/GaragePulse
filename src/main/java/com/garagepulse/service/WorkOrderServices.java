package com.garagepulse.service;

import java.util.List;

import com.garagepulse.dto.CreateWorkOrderRequestDto;
import com.garagepulse.dto.CreateWorkOrderResponseDto;
import com.garagepulse.dto.GetWorkOrderResponseDto;
import com.garagepulse.dto.UpdateWorkOrderDetailsDto;

public interface WorkOrderServices {

	CreateWorkOrderResponseDto createWorkOrder(CreateWorkOrderRequestDto dto);

	GetWorkOrderResponseDto getWorkOrderById(int id);

	List<GetWorkOrderResponseDto> getAllWorkOrders();

	List<GetWorkOrderResponseDto> getWorkOrdersByVehicleId(int vehicleId);

	GetWorkOrderResponseDto updateWorkOrder(int workOrderId, UpdateWorkOrderDetailsDto detailsDto);

	String deleteWorkOrder(int workOrderId);
}
