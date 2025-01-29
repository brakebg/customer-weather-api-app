package com.example.customerservice.dto;

import com.example.customerservice.model.TemperatureType;
import lombok.Data;

@Data
public class TemperatureCriteriaResponseDTO {
    private Long id;
    private TemperatureType type;
    private Double threshold;
}
