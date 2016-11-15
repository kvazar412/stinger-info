package org.dtmhapcs.service.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.dtmhapcs.service.EnumValidatorImpl;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = EnumValidatorImpl.class)

public @interface EnumValidator {
    String message() default "From EnumValidator.defaultMessage: not acceptable!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    String[] value();  
}