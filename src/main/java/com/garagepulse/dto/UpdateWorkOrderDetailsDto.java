package com.garagepulse.dto;

public class UpdateWorkOrderDetailsDto {
	
	String serviceType;
	String description;
	String status;
	double estimatedCost;
	public String getServiceType() {
		return serviceType;
	}
	public String getDescription() {
		return description;
	}
	public String getStatus() {
		return status;
	}
	public double getEstimatedCost() {
		return estimatedCost;
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
	public void setEstimatedCost(double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	
	

}
