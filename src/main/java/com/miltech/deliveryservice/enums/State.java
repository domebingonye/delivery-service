package com.miltech.deliveryservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum State {
    IDLE("IDLE"),
    LOADING("LOADING"),
    DELIVERING("DELIVERING"),
    RETURNING("RETURNING");

    @Getter
    private final String type;
}
