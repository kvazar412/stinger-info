package org.dtmhapcs.model.services.validators;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.dtmhapcs.model.services.validators.interfaces.EnumValidator;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private List<String> acceptableValues;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        acceptableValues = Arrays.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (acceptableValues.contains(value)) {
            return true;
        }
        return false;
    }
}