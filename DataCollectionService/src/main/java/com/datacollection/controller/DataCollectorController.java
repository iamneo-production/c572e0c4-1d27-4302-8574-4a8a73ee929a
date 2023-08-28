package com.datacollection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacollection.exceptions.ResourceNotFoundException;
import com.datacollection.externalservice.AlertGenrationService;
import com.datacollection.model.AlertMessage;
import com.datacollection.model.CircuitDemo;
import com.datacollection.model.PatientDTO;
import com.datacollection.model.PatientData;
import com.datacollection.model.VitalSignsDTO;
import com.datacollection.model.VitalSings;
import com.datacollection.service.DataCollectionService;
import com.datacollection.service.VitalSignService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/data")
@Slf4j
public class DataCollectorController {

	@Autowired
	private DataCollectionService dataCollectionService;

	@Autowired
	private VitalSignService vitalSignService;

	@Autowired
	private AlertGenrationService alertGenrationService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// patient login

	@PostMapping
	public ResponseEntity<PatientData> addPatient(@RequestBody PatientData patientData) {
		String encodedPassword = passwordEncoder.encode(patientData.getPassword());
		patientData.setPassword(encodedPassword);
		log.info("Login SuccessFul...{}", patientData.getName());
		return ResponseEntity.ok(dataCollectionService.addPatinetData(patientData));
	}

//	after login patient will add data not necessary now 

	@PostMapping("/health/{id}")
	public ResponseEntity<VitalSings> addHealthRecord(@RequestBody PatientDTO patientDTO, @PathVariable Long id) {

		VitalSings vitals = VitalSings.builder().patientId(id).glucoseLevel(patientDTO.getGlucoseLevel())
				.systolicPressure(patientDTO.getSystolicPressure()).diastolicPressure(patientDTO.getDiastolicPressure())
				.bodyTemperature(patientDTO.getBodyTemperature()).oxygenLevel(patientDTO.getOxygenLevel()).build();

		return ResponseEntity.ok(vitalSignService.addHealthRecord(vitals));

	}

	// testing feign client
	@PostMapping("/test")
	public ResponseEntity<AlertMessage> addpppp(@RequestBody PatientDTO patientDTO) {

		AlertMessage genrateAlert = alertGenrationService.genrateAlert(patientDTO);

		return new ResponseEntity<>(genrateAlert, HttpStatus.OK);

	}

	// main method for alert with health data

	@PostMapping("/alert/{id}")
	@CircuitBreaker(name = "recommendationBreaker", fallbackMethod = "patientFallback")
	public ResponseEntity<VitalSignsDTO> addhealthWithAlert(@RequestBody PatientDTO patientDTO, @PathVariable Long id) {

		if (dataCollectionService.getPatientById(id).getName() == null) {
			log.info("patient id is not present {}", id);
			throw new ResourceNotFoundException("Patient id is not present " + id);
		}

		//add vitals according to patient id
		VitalSings vitals = VitalSings.builder().patientId(id).glucoseLevel(patientDTO.getGlucoseLevel())
				.systolicPressure(patientDTO.getSystolicPressure()).diastolicPressure(patientDTO.getDiastolicPressure())
				.bodyTemperature(patientDTO.getBodyTemperature()).oxygenLevel(patientDTO.getOxygenLevel()).build();

		vitalSignService.addHealthRecord(vitals);

		patientDTO.setPatientId(id);
		patientDTO.setPatientName(dataCollectionService.getPatientById(id).getName());
		

		//we care calling alert generation miroservice
		AlertMessage genrateAlert = alertGenrationService.genrateAlert(patientDTO);

		VitalSignsDTO resultOfViatals = VitalSignsDTO.builder().patientId(id)
				.systolicPressure(patientDTO.getSystolicPressure()).diastolicPressure(patientDTO.getDiastolicPressure())
				.bodyTemperature(patientDTO.getBodyTemperature()).oxygenLevel(patientDTO.getOxygenLevel())
				.glucoseLevel(patientDTO.getGlucoseLevel()).message(genrateAlert.getMessage()).build();

		return new ResponseEntity<>(resultOfViatals, HttpStatus.OK);

	}

	// fall back method for circuit breaker
	public ResponseEntity<CircuitDemo> patientFallback(PatientDTO patientDTO, Long id, Throwable ex) {
		CircuitDemo circuitDemo = CircuitDemo.builder().issue("issue in service try after ssome time").id(1).build();
		return new ResponseEntity<>(circuitDemo, HttpStatus.OK);
	}

	// get all vitals by patient id

	@GetMapping("/allvitals/{patientId}")
	public ResponseEntity<List<VitalSings>> getVitalSignsByPatientId(@PathVariable Long patientId) {
		List<VitalSings> vitalSignsList = vitalSignService.getVitalSignsByPatientId(patientId);
		if (vitalSignsList.isEmpty()) {
			log.warn("vital is not presentcfor the patient id {}", patientId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(vitalSignsList, HttpStatus.OK);
	}

}
