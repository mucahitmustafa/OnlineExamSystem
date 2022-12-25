package com.mumu.Online.Exam.System.utils;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class SortUtil {

    public static Sort createSortModel(String sortStr, Sort.Direction defaultSortDirection) {
        defaultSortDirection = StringUtils.isEmpty(defaultSortDirection) ? Sort.Direction.ASC : defaultSortDirection;
        if (sortStr == null || sortStr.replaceAll(" ", "").equals("")) {
            return Sort.by(defaultSortDirection, "id");
        } else {
            // sortStr = 'sortDirection#sortProperty'
            String[] sortArray = sortStr.split("#");
            String sortProperty = sortArray[1];
            Sort sorted = Sort.by(Sort.Direction.valueOf(sortArray[0]), sortArray[1]);
            if (!sortProperty.equals("id")) {
                sorted = sorted.and(Sort.by(defaultSortDirection, "id"));
            }
            return sorted;
        }
    }
}
