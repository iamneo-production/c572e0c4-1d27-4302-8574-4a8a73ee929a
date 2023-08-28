package com.alertgeneration.model;

import lombok.Data;

@Data
public class EmailRequestDto {
	private long id;
	private long patientId;
	private String patientName;
	private String message;
}
