package com.example.customerservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import lombok.Data;
import java.util.List;

@Data
public class LocationRequestDTO {
    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @Valid
    @NotNull(message = "Temperature criteria is required")
    private List<TemperatureCriteriaDTO> criteria;
}
