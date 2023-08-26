package com.configurability.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.configurability.model.VitalSignConfigurability;
import com.configurability.service.ConfigurabilityService;

@RestController
@RequestMapping("/config")
public class ConfigurabilityController {

	@Autowired
	ConfigurabilityService configurabilityService;

	// doctors can add vitals according to patient condition
	@PostMapping
	public ResponseEntity<VitalSignConfigurability> addvitals(@RequestBody VitalSignConfigurability vitals) {
		return new ResponseEntity<>(configurabilityService.addVitals(vitals), HttpStatus.OK);
	}

	
	//doctor can see what default vital set if not set then dummy data will be set
	@GetMapping("/{patientId}")
	public ResponseEntity<VitalSignConfigurability> getVitalSignConfigByPatientId(@PathVariable Long patientId) {
		VitalSignConfigurability vitalSignConfig = configurabilityService.getVitalSignConfigByPatientId(patientId);

		if (vitalSignConfig == null) {

			VitalSignConfigurability dummyVitals = VitalSignConfigurability.builder().bodyTemperatureThreshold(100)
					.diastolicPressureThreshold(90).systolicPressureThreshold(120).glucoseLevelThreshold(80)
					.oxygenLevelThreshold(95).build();

			return ResponseEntity.ok(dummyVitals);
		}

		return ResponseEntity.ok(vitalSignConfig);
	}

	//doctor see all patient vital suggested
	@GetMapping("/all")
	public List<VitalSignConfigurability> getAllVitalSignConfigurabilities() {
		return configurabilityService.getAllVitalSignConfigurabilities();
	}

}
