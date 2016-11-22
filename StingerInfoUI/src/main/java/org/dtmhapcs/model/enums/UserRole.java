package org.dtmhapcs.model.enums;

import org.dtmhapcs.model.services.validators.interfaces.EnumValidator;

@EnumValidator(value = {"ADMIN", "USER", "DELETED"}, message = "From annotation: not acceptable!")
public enum UserRole {
    ADMIN, USER, DELETED
}