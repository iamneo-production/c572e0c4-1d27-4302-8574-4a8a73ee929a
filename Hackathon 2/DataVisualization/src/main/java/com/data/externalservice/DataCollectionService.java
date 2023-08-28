package com.data.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.data.model.VitalSings;

@FeignClient(name = "DATACOLLECTION-SERVICE")
public interface DataCollectionService {

	@GetMapping("/data/allvitals/{patientId}")
	List<VitalSings> getVitalSignsByPatientId(@PathVariable Long patientId);
}
