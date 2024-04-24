package com.kosign.redoapi.global.annotation;

import com.kosign.redoapi.constant.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PositiveOrZero;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@PositiveOrZero
@Digits(integer = Constants.EXCHANGE_RATE_INTEGER, fraction = Constants.EXCHANGE_RATE_FRACTION_DIGITS)
@Constraint(validatedBy = { })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
public @interface ExchangeRateAmount {
    String message() default "{Amount.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

