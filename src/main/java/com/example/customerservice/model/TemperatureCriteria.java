package com.example.customerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "temperature_criteria")
public class TemperatureCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Criteria type is required")
    private TemperatureType type;

    @NotNull(message = "Temperature threshold is required")
    private Double threshold;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
