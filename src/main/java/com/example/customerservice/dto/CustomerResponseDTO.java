package com.example.customerservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<LocationResponseDTO> locations;
}
