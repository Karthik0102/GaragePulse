package com.garagepulse.dto;

import java.time.LocalDateTime;

public class CreateWorkOrderResponseDto {

	int id;
	int vehicleId;
	String serviceType;
	String descritpion;
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

	public String getDescritpion() {
		return descritpion;
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

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
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
