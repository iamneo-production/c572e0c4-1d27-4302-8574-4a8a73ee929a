package com.alertgeneration.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alertgeneration.model.EmailRequestDto;
import com.alertgeneration.model.EmailResponseDto;

@FeignClient(name = "ALERT-SERVICE")
public interface AlertNotification {

	@PostMapping("/notification/sendAlert")
	EmailResponseDto sendAlert(@RequestBody EmailRequestDto emailDto);

}
