package com.garagepulse.dto;

public class CreateWorkOrderRequestDto {

	int vehicleId;
	String serviceType;
	String description;
	String status;
	double estimatedCost;

	public int getVehicleId() {
		return vehicleId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getEstimatedCost() {
		return estimatedCost;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEstimatedCost(double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

}
