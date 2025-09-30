package com.miltech.deliveryservice.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Item {
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Name can only contain letters, numbers, hyphen (-), and underscore (_)")
    private String name;
    @NotNull(message = "weight is required")
    private Double weight;
    private String code;
    private String boxCode;
}
