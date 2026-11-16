package com.garagepulse.dto;

import java.time.LocalDateTime;

public class GetWorkOrderResponseDto {

	int id;
	int vehicleId;
	String serviceType;
	String description;
	String status;
	LocalDateTime serviceDate;
	double estimatedCost;

	public int getId() {
		return id;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public LocalDateTime getServiceDate() {
		return serviceDate;
	}

	public double getEstimatedCost() {
		return estimatedCost;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setServiceDate(LocalDateTime serviceDate) {
		this.serviceDate = serviceDate;
	}

	public void setEstimatedCost(double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

}
