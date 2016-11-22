package org.dtmhapcs.model.services.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.dtmhapcs.model.services.validators.interfaces.EnumValidator;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String>{
    private String [] acceptableValues;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        acceptableValues = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (int i = 0; i < acceptableValues.length; i++){
            if (acceptableValues[i].equals(value)){
                return true;
            }
        }
        return false;
    }    
}
