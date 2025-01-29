package com.example.customerservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class LocationResponseDTO {
    private Long id;
    private String city;
    private Double latitude;
    private Double longitude;
    private List<TemperatureCriteriaResponseDTO> criteria;
}
