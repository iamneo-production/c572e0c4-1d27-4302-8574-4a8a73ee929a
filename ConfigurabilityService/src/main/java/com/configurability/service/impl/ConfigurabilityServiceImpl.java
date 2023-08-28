package com.configurability.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.configurability.model.VitalSignConfigurability;
import com.configurability.repository.VitalSIgnConfigurabilityRepository;
import com.configurability.service.ConfigurabilityService;

@Service
public class ConfigurabilityServiceImpl implements ConfigurabilityService {

	@Autowired
	VitalSIgnConfigurabilityRepository vitalSIgnConfigurabilityRepository;

	@Override
	public VitalSignConfigurability addVitals(VitalSignConfigurability vitalSignConfigurability) {
		return vitalSIgnConfigurabilityRepository.save(vitalSignConfigurability);
	}

	@Override
	public VitalSignConfigurability getVitalSignConfigByPatientId(Long patientId) {
		return vitalSIgnConfigurabilityRepository.findByPatientId(patientId);
	}

	@Override
	public List<VitalSignConfigurability> getAllVitalSignConfigurabilities() {
		return vitalSIgnConfigurabilityRepository.findAll();
	}

}
