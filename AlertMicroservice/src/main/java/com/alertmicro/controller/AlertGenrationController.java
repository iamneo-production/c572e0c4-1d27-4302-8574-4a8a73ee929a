package com.alertmicro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alertmicro.externalservice.AlertGenrationservice;
import com.alertmicro.model.AlertMessage;

@RestController
@RequestMapping("/notification")
public class AlertGenrationController {

	@Autowired
	private AlertGenrationservice alertGenrationservice;

	@GetMapping
	public ResponseEntity<List<AlertMessage>> allalerts() {
		return new ResponseEntity<>(alertGenrationservice.allalerts(), HttpStatus.OK);
	}

}
