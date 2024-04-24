package com.kosign.redoapi.enums;

import com.kosign.redoapi.component.AbstractEnumConverter;
import com.kosign.redoapi.component.AbstractEnumConverter;
import com.kosign.redoapi.component.GenericEnum;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

/**
 * A class can be used for getting Status enum
 */
public enum AuthProvider implements GenericEnum<AuthProvider, String> {
    DEV("DEV"),
    ADMIN("ADMIN"),
    PO("PO"),
    ;

    private final String value;

    AuthProvider(String value) {
        this.value = value;
    }

    /**
     * Method getValue  : Get Enum value
     * @return Enum value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Method getLabel  : Get Enum Label
     * @return Enum Label
     */
    @Override
    public String getLabel() {

        return switch (this){
            case DEV -> "DEV";
            case ADMIN -> "ADMIN";
            case PO -> "PO";
            default -> "Not supported yet.";
        };
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value  value that have to check
     * @return enum value
     */
    @JsonCreator
    public static AuthProvider fromValue(String value) {
        return Stream.of(AuthProvider.values()).filter(targetEnum -> targetEnum.value.equals(value)).findFirst().orElse(null);
    }

    public static class Converter extends AbstractEnumConverter<AuthProvider, String> {
        public Converter() {
            super(AuthProvider.class);
        }

    }
}
