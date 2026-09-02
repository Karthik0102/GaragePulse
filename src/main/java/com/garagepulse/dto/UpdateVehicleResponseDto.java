package com.garagepulse.dto;

public class UpdateVehicleResponseDto {

	int id;
	String vin;
	String make;
	String model;
	int year;
	String licensePlate;
	int customerId;

	public int getId() {
		return id;
	}

	public String getVin() {
		return vin;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
