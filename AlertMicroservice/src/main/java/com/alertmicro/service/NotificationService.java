package com.alertmicro.service;

import com.alertmicro.model.EmailRequestDto;
import com.alertmicro.model.EmailResponseDto;

public interface NotificationService {
	EmailResponseDto sendEmail(EmailRequestDto emailDto);
}
