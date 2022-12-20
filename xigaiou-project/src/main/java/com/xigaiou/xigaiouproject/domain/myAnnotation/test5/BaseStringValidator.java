package com.xigaiou.xigaiouproject.domain.myAnnotation.test5;


import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public abstract class BaseStringValidator<A extends Annotation> implements ConstraintValidator<A, String> {
    protected int maxLen;

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext){
        if(StringUtils.isEmpty(value)){
            return false;
        } else if(this.maxLen>0 && StringUtils.length(value) > this.maxLen){
            return false;
        } else {
            return this.isValid(value);
        }
    }

    protected abstract boolean isValid(String value);
}
