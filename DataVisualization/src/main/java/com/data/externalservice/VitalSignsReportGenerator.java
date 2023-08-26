package com.data.externalservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.model.VitalSings;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VitalSignsReportGenerator {

	@Autowired
	private DataCollectionService vitalSignsService;

	public List<Map<String, Object>> generateReport(Long patientId) {
		List<VitalSings> records = vitalSignsService.getVitalSignsByPatientId(patientId);

		if (records.isEmpty()) {
			log.info("record is not avalable with given patient id {}", patientId);
		}

		List<Map<String, Object>> reportList = new ArrayList<>();

		if (!records.isEmpty()) {
			for (VitalSings record : records) {
				Map<String, Object> reportItem = new HashMap<>();
				reportItem.put("recordId", record.getId());
				reportItem.put("timestamp", record.getTimestamp());
				reportItem.put("systolicPressure", record.getSystolicPressure());
				reportItem.put("diastolicPressure", record.getDiastolicPressure());
				reportItem.put("bodyTemperature", record.getBodyTemperature());
				reportItem.put("oxygenLevel", record.getOxygenLevel());
				reportItem.put("glucoseLevel", record.getGlucoseLevel());

				// Determine health condition based on vital signs
				String healthCondition = determineHealthCondition(record);
				reportItem.put("healthCondition", healthCondition);

				reportList.add(reportItem);
			}
		}

		return reportList;
	}

	private String determineHealthCondition(VitalSings record) {
		StringBuilder condition = new StringBuilder();

		if (record.getSystolicPressure() > 140 || record.getDiastolicPressure() > 90) {
			condition.append("High Blood Pressure, ");
		}

		if (record.getBodyTemperature() > 380) {
			condition.append("Fever, ");
		}

		if (record.getOxygenLevel() < 95) {
			condition.append("Low Oxygen Level, ");
		}

		if (record.getGlucoseLevel() > 140) {
			condition.append("High Glucose Level, ");
		}

		if (condition.length() == 0) {
			condition.append("Normal");
		} else {
			condition.setLength(condition.length() - 2); // Remove trailing comma and space
		}

		return condition.toString();

	}
}
