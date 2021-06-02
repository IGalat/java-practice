package com.jr.valid.practice._2_inherit;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AppleValidator implements ConstraintValidator<AppleCheck, Apple> {
    @Override
    public boolean isValid(Apple apple, ConstraintValidatorContext context) {
        if (apple.getSaturation() != null && apple.getSaturation() < 70) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Apples supply too low, need more apples plez").addConstraintViolation();
            return false;
        }
        return true;
    }
}
