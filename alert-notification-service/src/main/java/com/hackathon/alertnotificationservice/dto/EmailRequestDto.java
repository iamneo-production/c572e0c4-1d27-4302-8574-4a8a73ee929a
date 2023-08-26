package com.hackathon.alertnotificationservice.dto;

import lombok.Data;

@Data
public class EmailRequestDto {
    private int id;
    private int patientId;
    private String patientName;
    private String message;
}
