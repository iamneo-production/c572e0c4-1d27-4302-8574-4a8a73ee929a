package com.alertmicro.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.alertmicro.data.PatientMockDB;
import com.alertmicro.model.EmailRequestDto;
import com.alertmicro.model.EmailResponseDto;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

	private final JavaMailSender javaMailSender;
	private final ExecutorService executorService;

	@Value("${mail.id}")
	private String emailId;

	@Autowired
	public NotificationServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		this.executorService = Executors.newFixedThreadPool(2);
	}

	@Override
	public EmailResponseDto sendEmail(EmailRequestDto emailDto) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "UTF-8");
			mimeHelper.setTo(PatientMockDB.getConcernedDoctorEmail(emailDto.getPatientId()));
			mimeHelper.setSubject("VITALS ALERT");
			mimeHelper.setText("<h3 style=\"color: red;\">Vitals are out of range for patient "
					+ emailDto.getPatientName() + "!</h3><p>" + emailDto.getMessage() + "</p>", true);
			mimeHelper.setFrom(emailId, "Hackathon");
			executeSend(message);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return EmailResponseDto.builder().message("Alert has been sent to the concerned medical staffs.").build();
	}

	private void executeSend(MimeMessage message) {
		try {
			executorService.submit(() -> {
				javaMailSender.send(message);
				log.info("Mail Send Initiated");
			});
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
