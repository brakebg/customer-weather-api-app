package com.example.customerservice.controller;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer management APIs")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer with locations")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO) {
        CustomerResponseDTO createdCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all customers with pagination")
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CustomerResponseDTO> customers = customerService.getAllCustomers(pageRequest);
        return ResponseEntity.ok(customers);
    }
}
