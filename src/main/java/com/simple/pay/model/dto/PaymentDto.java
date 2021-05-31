package com.simple.pay.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simple.pay.model.validation.CurrencyConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Transaction format, used for the original request, audit output and for transaction retrieval.
 */
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

    @NotNull
    @Valid
    private CardHolder cardHolder;

    @NotNull
    @Valid
    private Card card;
}
