package com.miltech.deliveryservice.domain;

import com.miltech.deliveryservice.enums.State;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Box {
    @Schema(hidden = true)
    private Long id;
    @Size(max = 20, message = "txtReference must not be more than 20 characters")
    private String txtReference;
    @Max(value = 500, message = "Weight must not be more than 500")
    private double weight;
    @DecimalMax(value = "1.0", inclusive = true, message = "Battery level must not be more than 1.0")
    private double batteryLevel;
    private State state;
    private String boxCode;
}
