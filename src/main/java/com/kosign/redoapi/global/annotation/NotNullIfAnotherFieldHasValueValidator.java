package com.kosign.redoapi.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;

// Implement the ConstraintValidator interface.
public class NotNullIfAnotherFieldHasValueValidator implements ConstraintValidator<NotNullIfAnotherFieldHasValue, Object> {

    private String fieldName;
    private Object expectedFieldValue;
    private String dependFieldName;
    private boolean isNotBlank;

    @Override
    public void initialize(NotNullIfAnotherFieldHasValue annotation) {
        fieldName          = annotation.fieldName();
        expectedFieldValue = annotation.fieldValue();
        dependFieldName    = annotation.dependFieldName();
        isNotBlank         = annotation.isNotBlank();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(value);

        Object fieldValue   =  beanWrapper.getPropertyValue(fieldName);
        Object dependFieldValue   =  beanWrapper.getPropertyValue(dependFieldName);

        if(isNotBlank && Objects.nonNull(dependFieldValue) && StringUtils.isNotBlank(String.valueOf(dependFieldValue)) && (Objects.isNull(fieldValue) || StringUtils.isBlank(String.valueOf(fieldValue)))){
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();

            return false;
        }

        if(!isNotBlank && StringUtils.equals(String.valueOf(expectedFieldValue), String.valueOf(dependFieldValue)) && (Objects.isNull(fieldValue) || StringUtils.isBlank(String.valueOf(fieldValue)))){
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
