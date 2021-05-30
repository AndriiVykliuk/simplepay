package com.simple.pay.controller.api;

import com.simple.pay.exception.ServiceException;
import com.simple.pay.model.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDto> handleRunTimeException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> ObjectUtils.firstNonNull(error.getDefaultMessage(), ""),
                        (msg1, msg2) -> String.join(", ", msg1.toString(), msg2.toString())));
        log.error("Validation exception : {}", errors);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(errors));
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ResponseDto> handleRunTimeException(ServiceException e) {
        log.error("Exception : ", e);
        return ResponseEntity
                .status(e.getCode())
                .body(new ResponseDto(e.getMessages()));
    }
}
