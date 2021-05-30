package com.simple.pay.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CardHolder {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Email
    private String email;
}
