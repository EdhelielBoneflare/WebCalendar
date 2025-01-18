package org.gruzdeva.webcalendar.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private String pattern;

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

}
