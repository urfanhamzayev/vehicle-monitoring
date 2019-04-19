package com.urfan.vehicleservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="PING_SIGNAL")
public class PingSignal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated Ping ID.")
	private Long id;
	
	@Column(name="REGISTERATION_NUMBER", nullable = false)
	@ApiModelProperty(notes = "The vehicle Registeration Number.")
	private String registerationNumber;
	
	@Column(name="PING_DATE", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@ApiModelProperty(notes = "The timestamp of the Ping ID.")
	private ZonedDateTime pingDate = ZonedDateTime.now();

	public PingSignal() {
	}

	public PingSignal(String registerationNumber, ZonedDateTime pingDate) {
		this.registerationNumber = registerationNumber;
		this.pingDate = pingDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegisterationNumber() {
		return registerationNumber;
	}

	public void setRegisterationNumber(String registerationNumber) {
		this.registerationNumber = registerationNumber;
	}

	public ZonedDateTime getPingDate() {
		return pingDate;
	}

	public void setPingDate(ZonedDateTime pingDate) {
		this.pingDate = pingDate;
	}
}
