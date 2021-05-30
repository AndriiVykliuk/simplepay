package com.simple.pay.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field as a valid card PAN value.
 */
@NotNull
@Pattern(regexp = "[0-9]{16}", message = "PAN should be 16 digits long")
@Constraint(validatedBy = PANValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PANConstraint {

    String message() default "PAN should pass a Luhn check";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
