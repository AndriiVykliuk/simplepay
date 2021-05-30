package com.simple.pay.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@Pattern(regexp = "[0-9]{4}", message = "The expiry should be 4 digits mmYY")
@Constraint(validatedBy = ExpiryDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpiryDateConstraint {

    String message() default "The expiry date should not be in the past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
