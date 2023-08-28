package com.alertmicro.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertMessage {

	@Id
	private long id;
	private long patientId;
	private String patientName;
	private String message;

}
