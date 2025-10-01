package com.miltech.deliveryservice.exception;

import com.miltech.deliveryservice.domain.BaseResponse;
import com.miltech.deliveryservice.enums.ResponseCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<BaseResponse> handleException(RequestException e) {
        log.error(e.getCode(), e);
        return ResponseEntity.status(Integer.parseInt(e.getCode())).body(new BaseResponse(String.valueOf(e.getCode()), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleException(Exception e) {
        log.error("Error:::", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(ResponseCodes.INTERNAL_SERVER_ERROR.getCode(), ResponseCodes.INTERNAL_SERVER_ERROR.getMessage(), e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResponse> handleException(DataIntegrityViolationException e) {
        log.error("Error:::", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(ResponseCodes.INTERNAL_SERVER_ERROR.getCode(), !StringUtils.hasText(e.getMessage()) ? ResponseCodes.INTERNAL_SERVER_ERROR.getMessage() : e.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse> handleException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new BaseResponse((e.getMessage()), e.getMessage().split("\"")[1]));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<BaseResponse> handleException(MethodArgumentNotValidException e) {
        String message = ResponseCodes.BAD_REQUEST.getMessage();
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = result.getFieldErrors();
        if (!errorList.isEmpty()) {
            message += (". " + errorList.get(0).getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(ResponseCodes.INTERNAL_SERVER_ERROR.getCode(), message));
    }

}
