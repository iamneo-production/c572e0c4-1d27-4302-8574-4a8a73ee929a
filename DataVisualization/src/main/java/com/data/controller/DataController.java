package com.data.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.externalservice.DataCollectionService;
import com.data.externalservice.VitalSignsReportGenerator;
import com.data.model.VitalSings;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/testreport")
@Slf4j
public class DataController {

	@Autowired
	private DataCollectionService dataCollectionService;

	@Autowired
	private VitalSignsReportGenerator reportGenerator;

	//get patient details
	@GetMapping("/{patientId}")
	public ResponseEntity<List<VitalSings>> getVitalSignsByPatientId(@PathVariable Long patientId) {
		List<com.data.model.VitalSings> vitalSignsList = dataCollectionService.getVitalSignsByPatientId(patientId);
		if (vitalSignsList.isEmpty()) {
			// Handle the case when no data is found for the patientId
			log.info("Patient id is not present {}",patientId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(vitalSignsList, HttpStatus.OK);
	}

	
	//report generator
	@GetMapping("/generate/{patientId}")
	public ResponseEntity<List<Map<String, Object>>> generateReport(@PathVariable Long patientId) {
		List<Map<String, Object>> report = reportGenerator.generateReport(patientId);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

}
