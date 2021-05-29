package com.simple.pay.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDto {

    private boolean approved;
    private Map<String, Object> errors = new HashMap<>();

    public ResponseDto() {
        approved = true;
    }

    public ResponseDto(Map<String, Object> errors) {
        this.errors = errors;
    }
}
