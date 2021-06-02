package com.jr.valid.practice._2_inherit;

import com.jr.valid.practice.ValidatorTestBase;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class Inheritance extends ValidatorTestBase {
    @Test
    void allValid() {
        Apple apple = new Apple(100, 30, 90);
        Set<ConstraintViolation<Apple>> violations = validator().validate(apple);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void onlyNewConstraints() {
        Apple apple = new Apple(null, 10, 66);
        Set<ConstraintViolation<Apple>> violations = validator().validate(apple);
        Assertions.assertEquals(2, violations.size());
    }

    @Test
    void onlyInheritedConstraints() {
        Apple apple = new Apple(-5, -2, 90);
        Set<ConstraintViolation<Apple>> violations = validator().validate(apple);
        Assertions.assertEquals(4, violations.size());

        // All constraints were inherited. @PositiveOrZero both on variable and on getter; and class constraint
        // If constraint is duplicated in inherited class, it's treated as another constraint
        // So, no easy way to override validation level to lower one, only to higher
        Assertions.assertEquals(3, violations.stream()
                .filter(v -> v.getMessage().equals("must be greater than or equal to 0")).count());
        Assertions.assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("saturation")).count());
    }

    @Test
    void mixConstraints() {
        Apple apple = new Apple(null, -50, 20);
        Set<ConstraintViolation<Apple>> violations = validator().validate(apple);
        Assertions.assertEquals(3, violations.size());
    }
}
