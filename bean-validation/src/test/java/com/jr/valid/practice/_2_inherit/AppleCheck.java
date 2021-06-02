package com.jr.valid.practice._2_inherit;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AppleValidator.class})
@Documented
public @interface AppleCheck {
    String message() default "AppleCheck fail. THIS SHOULD NOT APPEAR";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}