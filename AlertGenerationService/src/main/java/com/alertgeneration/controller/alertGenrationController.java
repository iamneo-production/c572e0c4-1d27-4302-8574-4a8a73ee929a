package com.alertgeneration.controller;

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

import com.alertgeneration.externalservices.AlertNotification;
import com.alertgeneration.externalservices.ConfigurabilityService;
import com.alertgeneration.model.AlertMessage;
import com.alertgeneration.model.CircuitDemo;
import com.alertgeneration.model.EmailRequestDto;
import com.alertgeneration.model.EmailResponseDto;
import com.alertgeneration.model.PatientDTO;
import com.alertgeneration.model.VitalSignConfigurability;
import com.alertgeneration.service.AlertMessageService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/genrate")
@Slf4j
public class alertGenrationController {

	@Autowired
	private ConfigurabilityService configurabilityService;

	@Autowired
	private AlertMessageService alertMessageService;

	@Autowired
	private AlertNotification alertNotification;

	// testing feign client for the specific patient
	@GetMapping("/config/{patientId}")
	public ResponseEntity<VitalSignConfigurability> getVitalSignConfigByPatientId(@PathVariable Long patientId) {
		return new ResponseEntity<>(configurabilityService.getVitalSignConfigByPatientId(patientId), HttpStatus.OK);
	}

	// logic for checking the threshold values
	private void checkAndAppendMessage(String vitalName, int defaultValue, int originalValue,
			StringBuilder resultMessage) {
		int valueDifference = Math.abs(defaultValue - originalValue);

		log.info("difference value are : {}", valueDifference);

		if (originalValue < defaultValue && valueDifference > 18) {
			resultMessage.append(vitalName).append(" is Low, take action \n");
		} else if (originalValue > defaultValue && valueDifference > 18) {
			resultMessage.append(vitalName).append(" is High, take action \n");
		}
	}

//	alert generation logic (this called in patient vitals saved in data collection)

	@PostMapping()
	@CircuitBreaker(name = "recommendationBreaker", fallbackMethod = "alertFallback")
	public ResponseEntity<AlertMessage> genrateAlert(@RequestBody PatientDTO patientDTO) {

		// defined threshold value for patient received
		VitalSignConfigurability deafultDataSetByDoctor = configurabilityService
				.getVitalSignConfigByPatientId(patientDTO.getPatientId());

		StringBuilder resultMessage = new StringBuilder();

		// check body temperature
		long defaultBodyTemperature = deafultDataSetByDoctor.getBodyTemperatureThreshold();
		long originalBodyTemperature = patientDTO.getBodyTemperature();
		long bodyTemperatureDifference = Math.abs(defaultBodyTemperature - originalBodyTemperature);

		log.info("Body Temperature Difference {}", bodyTemperatureDifference);

		if (originalBodyTemperature < defaultBodyTemperature && bodyTemperatureDifference > 18) {
			resultMessage.append("Body Temperature is Low, take action\n");
		} else if (originalBodyTemperature > defaultBodyTemperature && bodyTemperatureDifference > 18) {
			resultMessage.append("Body Temperature is High, take action\n");
		}

		checkAndAppendMessage("Systolic Blood Pressure", deafultDataSetByDoctor.getSystolicPressureThreshold(),
				patientDTO.getSystolicPressure(), resultMessage);
		checkAndAppendMessage("Diastolic Blood Pressure", deafultDataSetByDoctor.getDiastolicPressureThreshold(),
				patientDTO.getDiastolicPressure(), resultMessage);
		checkAndAppendMessage("Oxygen Level", deafultDataSetByDoctor.getOxygenLevelThreshold(),
				patientDTO.getOxygenLevel(), resultMessage);
		checkAndAppendMessage("Glucose Level", deafultDataSetByDoctor.getGlucoseLevelThreshold(),
				patientDTO.getGlucoseLevel(), resultMessage);

		if (resultMessage.length() > 0) {
			AlertMessage alertMessage = new AlertMessage();
			alertMessage.setPatientId(patientDTO.getPatientId());
			alertMessage.setMessage(resultMessage.toString());
			alertMessage.setPatientName(patientDTO.getPatientName());

			
			//sending alert to email by calling alert service
			EmailRequestDto emailDto = new EmailRequestDto();
			emailDto.setId(patientDTO.getPatientId());
			emailDto.setPatientId(patientDTO.getPatientId());
			emailDto.setPatientName(patientDTO.getPatientName());
			emailDto.setMessage(resultMessage.toString());
			alertNotification.sendAlert(emailDto);

			return new ResponseEntity<>(alertMessageService.genrateAlert(alertMessage), HttpStatus.OK);
		}
		return new ResponseEntity<>(
				AlertMessage.builder().patientId(patientDTO.getPatientId()).message("Normal").build(), HttpStatus.OK);
	}

	// fall back method for circuit breaker
	public ResponseEntity<CircuitDemo> alertFallback(PatientDTO patientDTO, Throwable ex) {
		CircuitDemo circuitDemo = CircuitDemo.builder().issue("issue in service try after ssome time").id(1).build();
		return new ResponseEntity<>(circuitDemo, HttpStatus.OK);
	}

	// all alert
	@GetMapping("/all/alert")
	public ResponseEntity<List<AlertMessage>> allalerts() {
		return new ResponseEntity<>(alertMessageService.allAlerts(), HttpStatus.OK);
	}

	@PostMapping("/sendAlert")
	public ResponseEntity<EmailResponseDto> sendAlert(@RequestBody EmailRequestDto emailDto) {
		log.info("email sended");
		return new ResponseEntity<>(alertNotification.sendAlert(emailDto), HttpStatus.OK);
	}

}
