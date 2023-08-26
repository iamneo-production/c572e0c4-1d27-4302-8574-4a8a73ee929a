package com.data.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalSings {

	private Long id;
	private Long patientId;
	private int systolicPressure;
	private int diastolicPressure;
	private long bodyTemperature;
	private int oxygenLevel;
	private int glucoseLevel;
	private Date timestamp;

}