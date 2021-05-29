package com.simple.pay.controller.api;

import com.simple.pay.exception.ServiceException;
import com.simple.pay.model.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseDto> handleRunTimeException(RuntimeException e) {
        log.error("Exception : ", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(Map.of("error", "internal server error")));
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ResponseDto> handleRunTimeException(ServiceException e) {
        log.error("Exception : ", e);
        return ResponseEntity
                .status(e.getCode())
                .body(new ResponseDto(e.getMessages()));
    }
}
