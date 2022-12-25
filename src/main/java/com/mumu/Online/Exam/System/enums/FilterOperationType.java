package com.mumu.Online.Exam.System.enums;

public enum FilterOperationType {
    LIKE("#Like#"),
    LESS_THAN("#LessThan#"),
    GREATER_THAN("#GreaterThan#"),
    EQUAL("#Equal#"),
    NOT_EQUAL("#NotEqual#"),
    BOOLEAN("#Boolean#"),
    IN("#In#"),
    NULL("#Null#");

    private String value;

    FilterOperationType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
