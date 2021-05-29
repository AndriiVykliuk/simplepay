package com.simple.pay.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PayResponseDto {

    private boolean approved;
    private Map<String, String> errors = new HashMap<>();

}
