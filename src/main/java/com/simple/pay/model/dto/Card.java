package com.simple.pay.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simple.pay.model.validation.ExpiryDateConstraint;
import com.simple.pay.model.validation.PANConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Represents bank Card information.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {

    @PANConstraint
    private String pan;

    @ExpiryDateConstraint
    private String expiry;

    @NotNull
    @Pattern(regexp = "[0-9]{3}", message = "The cvv should be 3 digits")
    private String cvv;
}
