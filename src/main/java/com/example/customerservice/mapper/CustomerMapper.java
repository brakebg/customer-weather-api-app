package com.example.customerservice.mapper;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.dto.LocationRequestDTO;
import com.example.customerservice.dto.LocationResponseDTO;
import com.example.customerservice.dto.TemperatureCriteriaDTO;
import com.example.customerservice.dto.TemperatureCriteriaResponseDTO;
import com.example.customerservice.model.Customer;
import com.example.customerservice.model.Location;
import com.example.customerservice.model.TemperatureCriteria;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "locations", source = "locations")
    Customer toEntity(CustomerRequestDTO dto);

    CustomerResponseDTO toDTO(Customer entity);

    List<CustomerResponseDTO> toDTOList(List<Customer> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Location toEntity(LocationRequestDTO dto);

    LocationResponseDTO toDTO(Location entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", ignore = true)
    TemperatureCriteria toEntity(TemperatureCriteriaDTO dto);

    TemperatureCriteriaResponseDTO toDTO(TemperatureCriteria entity);

    @AfterMapping
    default void linkCustomerLocations(@MappingTarget Customer customer) {
        if (customer.getLocations() != null) {
            customer.getLocations().forEach(location -> location.setCustomer(customer));
        }
    }

    @AfterMapping
    default void linkLocationCriteria(@MappingTarget Location location) {
        if (location.getCriteria() != null) {
            location.getCriteria().forEach(criteria -> criteria.setLocation(location));
        }
    }
}
