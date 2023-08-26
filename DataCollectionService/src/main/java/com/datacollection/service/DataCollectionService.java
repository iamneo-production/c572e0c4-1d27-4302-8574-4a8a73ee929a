package com.datacollection.service;

import com.datacollection.model.PatientData;

public interface DataCollectionService {

	public PatientData addPatinetData(PatientData patientData);
	
	PatientData getPatientById(long id);
	

}
