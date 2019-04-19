package com.alten.vehiclesignalgenerator.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vehicle {

	private Long id;
	private String vehicleID;
	private String registerationNumber;
	private Long customerID;
	private String status;
	private ZonedDateTime lastPingDate = ZonedDateTime.now();

	public Vehicle() {
	}

	public Vehicle(String vehicleID, String registerationNumber, Long customerID, String status, ZonedDateTime lastPingDate) {
		this.vehicleID = vehicleID;
		this.registerationNumber = registerationNumber;
		this.customerID = customerID;
		this.status = status;
		this.lastPingDate = lastPingDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getRegisterationNumber() {
		return registerationNumber;
	}

	public void setRegisterationNumber(String registerationNumber) {
		this.registerationNumber = registerationNumber;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ZonedDateTime getLastPingDate() {
		return lastPingDate;
	}

	public void setLastPingDate(ZonedDateTime lastPingDate) {
		this.lastPingDate = lastPingDate;
	}
}
