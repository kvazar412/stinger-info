package org.dtmhapcs.model.services.validators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.dtmhapcs.model.services.validators.interfaces.EnumValidator;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private Set<String> acceptableValues;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        acceptableValues = new HashSet<String>(Arrays.asList(constraintAnnotation.value()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return acceptableValues.contains(value);
    }

    public Set<String> getAcceptableValues() {
        return acceptableValues;
    }
}