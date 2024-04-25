package com.kosign.redoapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.kosign.redoapi.component.AbstractEnumConverter;
import com.kosign.redoapi.component.GenericEnum;

import java.util.stream.Stream;

public enum ProjectStatus implements GenericEnum<ProjectStatus, String> {

    APPROVE("1"),
    REJECT("2")
    ;

    private final String value;

    ProjectStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return switch (this) {
            case APPROVE -> "APPROVE";
            case REJECT -> "REJECT";
            default -> "Not supported yet";
        };
    }

    @JsonCreator
    public static ProjectStatus fromValue(String value) {
        return Stream.of(ProjectStatus.values()).filter(targetEnum -> targetEnum.value.equals(value)).findFirst().orElse(null);
    }

    public static class Converter extends AbstractEnumConverter<ProjectStatus, String> {
        public Converter() {
            super(ProjectStatus.class);
        }
    }

}
