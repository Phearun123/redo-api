package com.kosign.redoapi.utils;


import org.apache.commons.lang3.StringUtils;

public abstract class BaseSpecification<T, U> {

    public static final String wildcard = "%";

    protected String containsLowerCase(String searchField) {
        return wildcard + searchField.toLowerCase() + wildcard;
    }

    public static String containLowerCase(String searchField) {

        if(StringUtils.isNoneBlank(searchField)){
            return wildcard + searchField.toLowerCase() + wildcard;
        }

        return null;

    }
    public static String containUpperCase(String searchField) {

        return wildcard + searchField.toUpperCase() + wildcard;

    }
}
