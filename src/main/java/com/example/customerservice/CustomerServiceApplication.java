package com.example.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Customer Service API",
        version = "1.0",
        description = "API for managing customer information and their location preferences"
    )
)
@EntityScan(basePackages = "com.example.customerservice.model")
@EnableJpaRepositories(basePackages = "com.example.customerservice.repository")
@ComponentScan(basePackages = "com.example.customerservice", lazyInit = true)
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
