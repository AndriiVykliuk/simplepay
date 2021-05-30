package com.simple.pay.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

/**
 * Currency value validator, based on java.util.Currency supported values.
 */
public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, String> {

    @Override
    public void initialize(CurrencyConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext cvc) {
        try {
            Currency.getInstance(field);
            return true;
        } catch (Exception e) {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate("'" + field + "' is not a valid currency code.")
                    .addConstraintViolation();
            return false;
        }
    }
}
