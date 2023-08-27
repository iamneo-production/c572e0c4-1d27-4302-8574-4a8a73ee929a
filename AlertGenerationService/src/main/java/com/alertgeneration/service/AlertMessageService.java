package com.alertgeneration.service;

import com.alertgeneration.model.AlertMessage;
import java.util.List;

public interface AlertMessageService {

	AlertMessage genrateAlert(AlertMessage alertMessage);

	List<AlertMessage> allAlerts();

}
