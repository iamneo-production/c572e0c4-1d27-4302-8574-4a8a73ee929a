package com.alertgeneration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalSignConfigurability {

	private long id;
	private long patientId;
	private int systolicPressureThreshold;
	private int diastolicPressureThreshold;
	private long bodyTemperatureThreshold;
	private int oxygenLevelThreshold;
	private int glucoseLevelThreshold;

}
