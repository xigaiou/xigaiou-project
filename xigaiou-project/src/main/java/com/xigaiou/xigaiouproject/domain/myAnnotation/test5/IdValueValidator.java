package com.xigaiou.xigaiouproject.domain.myAnnotation.test5;

public class IdValueValidator extends BaseStringValidator<IdValue>{
    IdValueValidator(){}

    public void initialize(IdValue idParam){

    }

    @Override
    protected boolean isValid(String value) {
        return false;
    }
}
