package com.simple.pay.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simple.pay.model.validation.CurrencyConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {

    @NotNull
    private Integer invoice;

    @NotNull
    @Min(value = 1)
    private Integer amount;

    @CurrencyConstraint
    private String currency;

    @Valid
    private CardHolder cardHolder;

    @Valid
    private Card card;
}
