package com.xigaiou.xigaiouproject.common.gqlSupport;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class NameConvertUtil {
    public static String toCamelCase(String underName){
        StringBuilder name = new StringBuilder(underName.length());
        boolean toUpper = false;
        char[] underNameArray = underName.toCharArray();
        int arrayLength = underNameArray.length;

        for(int i = 0; i< arrayLength; ++i){
            char oneUnderName = underNameArray[i];
            if(oneUnderName == '_'){
                if(name.length() > 0){
                    toUpper = true;
                }
            } else {
                if(toUpper){
                    name.append(Character.toUpperCase(oneUnderName));
                } else {
                    name.append(Character.toLowerCase(oneUnderName));
                }
                toUpper = false;
            }
        }
        return name.toString();
    }
}
