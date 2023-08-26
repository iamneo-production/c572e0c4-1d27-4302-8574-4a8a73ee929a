package com.hackathon.alertnotificationservice.controller;

import com.hackathon.alertnotificationservice.dto.EmailRequestDto;
import com.hackathon.alertnotificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertNotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/sendAlert")
    public void sendAlert(EmailRequestDto emailDto){
        EmailRequestDto emailRequestDto = new EmailRequestDto();
        notificationService.sendEmail(emailDto);
    }
}
