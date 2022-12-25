package com.mumu.Online.Exam.System.utils;

import com.mumu.Online.Exam.System.enums.FilterOperationType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static Matcher getFilterMatcher(String filterString) {
        Pattern pattern = Pattern.compile(
                "(\\w+)(" + FilterOperationType.LIKE.getValue()
                        + "|" + FilterOperationType.LESS_THAN.getValue()
                        + "|" + FilterOperationType.GREATER_THAN.getValue()
                        + "|" + FilterOperationType.EQUAL.getValue()
                        + "|" + FilterOperationType.NOT_EQUAL.getValue()
                        + "|" + FilterOperationType.BOOLEAN.getValue()
                        + "|" + FilterOperationType.IN.getValue()
                        + "|" + FilterOperationType.NULL.getValue()
                        + ")(.*)",
                Pattern.UNICODE_CHARACTER_CLASS);
        return pattern.matcher(filterString);
    }
}
