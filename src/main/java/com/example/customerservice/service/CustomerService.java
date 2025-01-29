package com.example.customerservice.service;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.model.Customer;
import com.example.customerservice.model.Location;
import com.example.customerservice.model.TemperatureCriteria;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.exception.CustomerAlreadyExistsException;
import com.example.customerservice.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerDTO) {
        // Check if customer exists first
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new CustomerAlreadyExistsException(customerDTO.getEmail());
        }

        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customerMapper::toDTO);
    }
}
