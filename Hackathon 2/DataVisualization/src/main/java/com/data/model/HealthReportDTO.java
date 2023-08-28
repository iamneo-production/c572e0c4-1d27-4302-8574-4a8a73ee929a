package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthReportDTO {

	private Long recordId;
	private String timestamp;
	private int systolicPressure;
	private int diastolicPressure;
	private long bodyTemperature;
	private int oxygenLevel;
	private int glucoseLevel;
	private String healthCondition;

}
