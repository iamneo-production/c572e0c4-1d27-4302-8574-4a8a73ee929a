package com.datacollection.service;

import java.util.List;

import com.datacollection.model.VitalSings;

public interface VitalSignService {

	public VitalSings addHealthRecord(VitalSings vitalSings);

	List<VitalSings> getVitalSignsByPatientId(Long patientId);

}
