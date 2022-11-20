package com.springboot.boxview;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating, String> {
    @Override
    public void initialize(Rating rating) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Double number;
        try {
            number = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return false;
        }

        if (number > 10 || number <1) {
            return false;
        }
        return true;
    }
}
