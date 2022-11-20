package com.springboot.boxview;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class PriorityValidator implements ConstraintValidator<Priority,String>{

    @Override
    public void initialize(Priority priority) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.trim().length()==1 && "LHM".contains(value.trim());
    }
}
