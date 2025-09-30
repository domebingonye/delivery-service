package com.miltech.deliveryservice.exception;

public class RequestException extends RuntimeException{
    private String code;
    private String message;

    public RequestException(String message){
        super(message);
    }

    public RequestException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
