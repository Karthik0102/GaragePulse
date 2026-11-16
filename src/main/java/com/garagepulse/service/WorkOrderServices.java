package com.garagepulse.service;

import com.garagepulse.dto.CreateWorkOrderRequestDto;
import com.garagepulse.dto.CreateWorkOrderResponseDto;

public interface WorkOrderServices {

	CreateWorkOrderResponseDto createWorkOrder(CreateWorkOrderRequestDto dto);

}
