package com.miltech.deliveryservice.domain;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class ItemsRequest {
    private List<String> itemCodes;
    @Valid
    private List<Box> boxList;
}
