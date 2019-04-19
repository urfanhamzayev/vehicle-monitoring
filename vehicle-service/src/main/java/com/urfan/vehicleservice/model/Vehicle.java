package com.urfan.vehicleservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated vehicle ID.")
	private Long id;

	@NotNull
	@Size(min = 17)
	@Column(name = "VEHICLE_ID")
	@ApiModelProperty(notes = "The vehicle identifier.")
	private String vehicleID;

	@NotNull
	@Size(min = 6)
	@Column(name = "REGISTERATION_NUMBER")
	@ApiModelProperty(notes = "The vehicle regiteration number.")
	private String registerationNumber;

	@NotNull
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes = "The vehicle customer.")
	private Long customerID;

	@Column(nullable = false)
	private String status;

	@Column(name="LAST_PING_DATE", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
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
