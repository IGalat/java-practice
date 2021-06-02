package com.jr.valid.practice;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ValidatorTestBase {
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
