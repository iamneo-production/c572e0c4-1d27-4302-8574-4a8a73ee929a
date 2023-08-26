package com.hackathon.alertnotificationservice.service;

import com.hackathon.alertnotificationservice.dto.EmailRequestDto;
import com.hackathon.alertnotificationservice.dto.EmailResponseDto;

public interface NotificationService {
    EmailResponseDto sendEmail(EmailRequestDto emailDto);
}
