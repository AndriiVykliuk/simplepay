package com.simple.pay.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ExpiryDateValidator implements ConstraintValidator<ExpiryDateConstraint, String> {

    public static final String EXPIRY_REGEX = "^(1[0-2]|0[1-9])([0-9]{2})$";

    @Override
    public void initialize(ExpiryDateConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String expiry, ConstraintValidatorContext cvc) {
        // this check has been processed and error created already.
        if (!expiry.matches(EXPIRY_REGEX)) {
            return true;
        }
        int year = Integer.parseInt("20" + expiry.substring(2));
        int month = Integer.parseInt(expiry.substring(0, 2));
        return LocalDate.of(year, month, 2).isAfter(LocalDate.now().withDayOfMonth(1));
    }

}
