package com.miltech.deliveryservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResponseCodes {
    SUCCESS("00", "Success"),
    FAILURE("01", "Failure"),
    PENDING("02", "Pending"),
    BAD_REQUEST("400", "Bad request"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error");

    @Getter
    private final String code;
    @Getter
    private final String message;
}
