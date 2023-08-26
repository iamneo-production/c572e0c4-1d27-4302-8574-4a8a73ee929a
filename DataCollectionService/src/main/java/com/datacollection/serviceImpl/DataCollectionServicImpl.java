package com.datacollection.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacollection.exceptions.ResourceNotFoundException;
import com.datacollection.model.PatientData;
import com.datacollection.repository.PatientDataRepository;
import com.datacollection.service.DataCollectionService;

@Service
public class DataCollectionServicImpl implements DataCollectionService {

	@Autowired
	private PatientDataRepository patientDataRepository;

	@Override
	public PatientData addPatinetData(PatientData patientData) {
		return patientDataRepository.save(patientData);
	}

	@Override
	public PatientData getPatientById(long id) {
		return patientDataRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient id is not present " + id));
	}

}
