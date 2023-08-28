package com.configurability.service;

import java.util.List;

import com.configurability.model.VitalSignConfigurability;

public interface ConfigurabilityService {

	public VitalSignConfigurability addVitals(VitalSignConfigurability vitalSignConfigurability);

	VitalSignConfigurability getVitalSignConfigByPatientId(Long patientId);

	List<VitalSignConfigurability> getAllVitalSignConfigurabilities();
}
