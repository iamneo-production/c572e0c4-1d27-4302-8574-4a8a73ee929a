package com.datacollection.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.datacollection.model.AlertMessage;
import com.datacollection.model.PatientDTO;

@FeignClient(name = "ALERTGENERATION-SERVICE")
public interface AlertGenrationService {

	@PostMapping("/genrate")
	AlertMessage genrateAlert(@RequestBody PatientDTO patientDTO);

}
