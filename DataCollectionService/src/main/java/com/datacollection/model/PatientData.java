package com.datacollection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PatientData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String name;
	private String password;

//	private List<VitalSings> vitalSignsList=List.of();

}
