package com.alertgeneration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

	
	private Long patientId;
	private String patientName;
	private int systolicPressure;
	private int diastolicPressure;
	private long bodyTemperature;
	private int oxygenLevel;
	private int glucoseLevel;

}
