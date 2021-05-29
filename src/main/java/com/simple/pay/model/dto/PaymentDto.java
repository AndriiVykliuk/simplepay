package com.simple.pay.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {

    private Integer invoice;
    private Integer amount;
    private String currency;

    private CardHolder cardHolder;
    private Card card;
}
