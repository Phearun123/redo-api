package com.kosign.redoapi.global.annotation;

import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Repeatable(NotNullIfAnotherFieldHasValue.List.class) // only with hibernate-validator >= 6.x
@Constraint(validatedBy = NotNullIfAnotherFieldHasValueValidator.class)
@Documented
public @interface NotNullIfAnotherFieldHasValue {
    String fieldName();
    String fieldValue() default "";
    boolean isNotBlank() default false;
    String dependFieldName();

    String message() default "{NotNullIfAnotherFieldHasValue.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotNullIfAnotherFieldHasValue[] value();
    }
}
