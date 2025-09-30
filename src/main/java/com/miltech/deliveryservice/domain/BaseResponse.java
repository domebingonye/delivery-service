package com.miltech.deliveryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {
    private String message;
    private String code;

    public BaseResponse(String message){
        this.message = message;
    }

    public BaseResponse(String code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseResponse(String code, String message, Object response){
        this.code = code;
        this.message = message;
    }
}
