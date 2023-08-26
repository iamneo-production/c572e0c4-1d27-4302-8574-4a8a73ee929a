package com.hackathon.alertnotificationservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailResponseDto {
    private String message;
}
