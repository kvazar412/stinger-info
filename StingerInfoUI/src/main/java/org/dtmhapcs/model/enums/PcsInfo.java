package org.dtmhapcs.model.enums;

import org.dtmhapcs.service.interfaces.EnumValidator;

@EnumValidator(value = {"YES", "NO", "UNKNOWN"}, message = "From annotation: not acceptable!") 
public enum PcsInfo {
    YES, NO, UNKNOWN
}