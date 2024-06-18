package com.project.libraryManagement.customValdators.dateRangeValidator;

import com.project.libraryManagement.models.core.Loan;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Loan> {

    @Override
    public boolean isValid(Loan arg0, ConstraintValidatorContext arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValid'");
    }

}
