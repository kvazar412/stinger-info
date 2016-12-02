package org.dtmhapcs.model.interfaces;

public interface BaseModelTest {
    void hashCodeForEqualsIsEqual();
    void equalsForBoundaryConditions();
    void equalsWithDefaultModel();
    void equalsWithOtherModel();
    void equalsWithSameModel();
    void toStringContainsId();
}