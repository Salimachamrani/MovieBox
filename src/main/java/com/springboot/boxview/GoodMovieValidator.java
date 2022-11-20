package com.springboot.boxview;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie,Movie> {
    @Override
    public void initialize(GoodMovie goodMovie) {

    }

    @Override
    public boolean isValid(Movie movie, ConstraintValidatorContext constraintValidatorContext) {
        return !(Double.valueOf(movie.getRating()) >= 8 &&  "L".equals(movie.getPriority()));
    }
}
