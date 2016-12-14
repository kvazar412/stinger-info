package org.dtmhapcs.model.services.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EnumValidatorImplTest {

    @Mock
    private ConstraintValidatorContext contextMock;

    @Mock
    private Set<String> acceptableValues;

    @InjectMocks
    private EnumValidatorImpl enumValidator;

    @Test
    public void testIsValidReturnsTrueForAcceptableValue() {
        when(acceptableValues.contains("2")).thenReturn(true);

        assertTrue(enumValidator.isValid("2", contextMock));
    }

    @Test
    public void testIsValidReturnsFalseForNonAcceptableValue() {
        when(acceptableValues.contains("4")).thenReturn(false);

        assertFalse(enumValidator.isValid("4", contextMock));
    }
}