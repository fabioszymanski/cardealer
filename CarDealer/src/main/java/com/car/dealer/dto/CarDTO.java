package com.car.dealer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarDTO {

	public CarDTO(String model, String make, String version, Integer yearRelease, Double price, Double fuelConsumption,
			Double annualMaintenanceCosts) {
		super();
		this.model = model;
		this.make = make;
		this.version = version;
		this.yearRelease = yearRelease;
		this.price = price;
		this.fuelConsumption = fuelConsumption;
		this.annualMaintenanceCosts = annualMaintenanceCosts;
	}
	
	@JsonProperty(required = true)
	private String model;
	
	@JsonProperty(required = true)
	private String make;
	
	@JsonProperty(required = true)
	private String version;
	
	@JsonProperty(required = true)
	private Integer yearRelease;
	
	@JsonProperty(required = true)
	private Double price;
	
	@JsonProperty(required = true)
	private Double fuelConsumption;
	
	@JsonProperty(required = true)
	private Double annualMaintenanceCosts;
	
	@JsonProperty(required = false)
	private Double totalCosts;
}
