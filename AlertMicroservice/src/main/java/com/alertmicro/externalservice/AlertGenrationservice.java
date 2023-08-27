package com.alertmicro.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.alertmicro.model.AlertMessage;

@FeignClient(name = "ALERTGENERATION-SERVICE")
public interface AlertGenrationservice {

	@GetMapping("/genrate/all/alert")
	List<AlertMessage> allalerts();
}
