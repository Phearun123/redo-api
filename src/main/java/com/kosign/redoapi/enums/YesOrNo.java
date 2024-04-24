package com.kosign.redoapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kosign.redoapi.component.AbstractEnumConverter;
import com.kosign.redoapi.component.GenericEnum;
import com.kosign.redoapi.component.AbstractEnumConverter;
import com.kosign.redoapi.component.GenericEnum;

/**
 * A class can be used for getting YesOrNo enum
 */
public enum YesOrNo implements GenericEnum<YesOrNo, String> {
    YES("Y"),
    NO("N"),
    ;

    private final String value;

    YesOrNo(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static YesOrNo fromValue(String value) {
        for(YesOrNo my: YesOrNo.values()) {
            if(my.value.equals(value)) {
                return my;
            }
        }

        return null;
    }

    /**
     * Method getValue : Get Enum value
     * @return Enum value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Method getLabel : Get Enum Label
     * @return Enum Label
     */
    @Override
    public String getLabel() {
        String label = "(no label)";

        if("Y".equals(value)) label = "Yes";
        else if("N".equals(value)) label = "No";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<YesOrNo, String> {

        public Converter() {
            super(YesOrNo.class);
        }

    }

}