package com.alertgeneration.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alertgeneration.model.AlertMessage;
import com.alertgeneration.repository.AlertMessageRepository;
import com.alertgeneration.service.AlertMessageService;
import java.util.List;

@Service
public class AlertMessageServiceImpl implements AlertMessageService {

	@Autowired
	private AlertMessageRepository alertMessageRepository;

	@Override
	public AlertMessage genrateAlert(AlertMessage alertMessage) {
		return alertMessageRepository.save(alertMessage);
	}

	@Override
	public List<AlertMessage> allAlerts() {
		return alertMessageRepository.findAll();
	}

}
