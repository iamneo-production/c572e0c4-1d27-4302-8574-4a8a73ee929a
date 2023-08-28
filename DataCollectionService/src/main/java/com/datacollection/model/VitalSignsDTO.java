package com.datacollection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VitalSignsDTO {
	
	private Long patientId;
	private int systolicPressure;
	private int diastolicPressure;
	private long bodyTemperature;
	private int oxygenLevel;
	private int glucoseLevel;
	private String message;

}
