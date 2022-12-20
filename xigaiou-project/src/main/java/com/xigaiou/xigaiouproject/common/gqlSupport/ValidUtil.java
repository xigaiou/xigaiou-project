package com.xigaiou.xigaiouproject.common.gqlSupport;

import java.util.regex.Pattern;

public final class ValidUtil {
    private static final Pattern REGEX_FIELD_ID = Pattern.compile("^[a-zA-Z]+[\\w\\d$]*$");
    public static boolean isValidMultiFieldId(String fieldId){
        String[] values = fieldId.split(",");
        String[] temp = values;
        int valuesLength = values.length;
        for(int i = 0; i < valuesLength; ++i){
            String value = temp[i];
            boolean ok = isValidFieldId(value);
            if(!ok){
                return false;
            }
        }
        return true;
    }
    public static boolean isValidFieldId(String fieldId){
        return REGEX_FIELD_ID.matcher(fieldId).matches();
    }
}
