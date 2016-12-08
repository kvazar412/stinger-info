package org.dtmhapcs.model.services.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

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
    private List<String> acceptableValues;

    @InjectMocks
    private EnumValidatorImpl enumValidatorMock;

    @Test
    public void isValidShouldReturnTrueForAcceptableValue() {
        when(acceptableValues.contains("2")).thenReturn(true);
        assertTrue(enumValidatorMock.isValid("2", contextMock));
    }

    @Test
    public void isValidShouldReturnFalseForNonAcceptableValue() {
        when(acceptableValues.contains("4")).thenReturn(false);
        assertFalse(enumValidatorMock.isValid("4", contextMock));
    }
}