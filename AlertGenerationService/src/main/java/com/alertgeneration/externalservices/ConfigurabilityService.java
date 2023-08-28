package com.alertgeneration.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.alertgeneration.model.VitalSignConfigurability;

@FeignClient(name = "CONFIGURABILITY-SERVICE")
public interface ConfigurabilityService {

	@GetMapping("/config/{patientId}")
	VitalSignConfigurability getVitalSignConfigByPatientId(@PathVariable Long patientId);

}
