package com.example.customerservice.dto;

import com.example.customerservice.model.TemperatureType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TemperatureCriteriaDTO {
    @NotNull(message = "Criteria type is required")
    private TemperatureType type;

    @NotNull(message = "Temperature threshold is required")
    private Double threshold;
}
