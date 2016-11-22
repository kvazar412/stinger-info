package org.dtmhapcs.model.enums;

import org.dtmhapcs.model.services.validators.interfaces.EnumValidator;

@EnumValidator(value = {"YES", "NO", "UNKNOWN"}, message = "From annotation: not acceptable!") 
public enum PcsInfo {
    YES, NO, UNKNOWN
}