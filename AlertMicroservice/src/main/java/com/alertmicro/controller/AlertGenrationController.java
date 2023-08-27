package com.alertmicro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alertmicro.externalservice.AlertGenrationservice;
import com.alertmicro.model.AlertMessage;
import com.alertmicro.model.EmailRequestDto;
import com.alertmicro.model.EmailResponseDto;
import com.alertmicro.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notification")
@Slf4j
public class AlertGenrationController {

	@Autowired
	private AlertGenrationservice alertGenrationservice;

	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public ResponseEntity<List<AlertMessage>> allalerts() {
		return new ResponseEntity<>(alertGenrationservice.allalerts(), HttpStatus.OK);
	}

	@PostMapping("/sendAlert")
	public ResponseEntity<EmailResponseDto> sendAlert(@RequestBody EmailRequestDto emailDto) {
		log.info("email sended");
		return new ResponseEntity<>(notificationService.sendEmail(emailDto), HttpStatus.OK);
	}

}
