package com.jr.valid.practice._1_basic;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MarketGoodSaturationValidator.class})
@Documented
public @interface MarketGoodCheck {
    String message() default "MarketGoodCheck fail. THIS SHOULD NOT APPEAR";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}