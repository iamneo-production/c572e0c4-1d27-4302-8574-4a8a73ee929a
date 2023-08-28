package com.datacollection.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacollection.model.VitalSings;
import com.datacollection.repository.VitalSignRepository;
import com.datacollection.service.VitalSignService;

@Service
public class VitalSignServiceImpl implements VitalSignService {

	@Autowired
	private VitalSignRepository vitalSignRepository;

	@Override
	public VitalSings addHealthRecord(VitalSings vitalSings) {
		return vitalSignRepository.save(vitalSings);
	}

	@Override
	public List<VitalSings> getVitalSignsByPatientId(Long patientId) {
		return vitalSignRepository.findByPatientId(patientId);
	}

}
