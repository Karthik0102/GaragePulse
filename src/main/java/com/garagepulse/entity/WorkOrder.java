package com.garagepulse.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WorkOrder")
public class WorkOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "Service Type")
	String serviceType;

	@Column(name = "Service Description")
	String description;

	@Column(name = "Service Status")
	String status;

	@Column(name = "Service Date")
	LocalDateTime serviceDate;

	@Column(name = "Service Cost")
	double estimatedCost;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	public int getId() {
		return id;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
