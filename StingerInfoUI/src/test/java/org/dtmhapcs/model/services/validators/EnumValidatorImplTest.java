package org.dtmhapcs.model.services.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidatorContext;

import org.dtmhapcs.model.services.validators.interfaces.EnumValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EnumValidatorImplTest {

    @Mock
    private EnumValidator constraintAnnotationMock;

    @Mock
    private ConstraintValidatorContext contextMock;

    @Mock
    private Set<String> acceptableValuesMock;

    @InjectMocks
    private EnumValidatorImpl enumValidator;

    private String[] value = { "1", "2", "3" };

    @Test
    public void testInitialize() {
        Set<String> acceptableValues = new HashSet<String>(Arrays.asList(value));

        when(constraintAnnotationMock.value()).thenReturn(value);
        enumValidator.initialize(constraintAnnotationMock);
        Set<String> acceptableValuesFromValidator = enumValidator.getAcceptableValues();

        assertTrue(acceptableValues.size() == acceptableValuesFromValidator.size());
        assertTrue(acceptableValues.containsAll(acceptableValuesFromValidator));
    }

    @Test
    public void testIsValidReturnsTrueForAcceptableValue() {
        when(acceptableValuesMock.contains("2")).thenReturn(true);

        assertTrue(enumValidator.isValid("2", contextMock));
    }

    @Test
    public void testIsValidReturnsFalseForNonAcceptableValue() {
        when(acceptableValuesMock.contains("4")).thenReturn(false);

        assertFalse(enumValidator.isValid("4", contextMock));
    }
}