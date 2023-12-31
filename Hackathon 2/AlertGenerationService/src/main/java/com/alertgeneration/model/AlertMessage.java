package com.alertgeneration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AlertMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long patientId;
	private String patientName;
	private String message;

}
