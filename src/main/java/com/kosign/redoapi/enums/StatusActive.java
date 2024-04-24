package com.kosign.redoapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.kosign.redoapi.component.AbstractEnumConverter;
import com.kosign.redoapi.component.GenericEnum;

import java.util.stream.Stream;

public enum StatusActive implements GenericEnum<StatusActive, String> {

    ACTIVATE("0"),
    DEACTIVATE("1")
    ;

    private final String value;

    StatusActive(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return switch (this) {
            case ACTIVATE -> "DEVELOPER";
            case DEACTIVATE -> "ADMIN";
            default -> "Not supported yet";
        };
    }

    @JsonCreator
    public static StatusActive fromValue(String value) {
        return Stream.of(StatusActive.values()).filter(targetEnum -> targetEnum.value.equals(value)).findFirst().orElse(null);
    }

    public static class Converter extends AbstractEnumConverter<StatusActive, String> {
        public Converter() {
            super(StatusActive.class);
        }
    }

}
