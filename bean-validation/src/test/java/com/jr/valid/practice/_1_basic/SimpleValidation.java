package com.jr.valid.practice._1_basic;

import com.jr.valid.frame.model.ValidationLevel;
import com.jr.valid.practice.ValidatorTestBase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SimpleValidation extends ValidatorTestBase {
    @Test
    void allValid() {
        MarketGood good = new MarketGood(100, 40, 90);
        Set<ConstraintViolation<MarketGood>> violations = validator().validate(good);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void invalidSaturation() {
        MarketGood good = new MarketGood(100, 40, -1000);
        Set<ConstraintViolation<MarketGood>> violations = validator().validate(good);
        Assertions.assertEquals(2, violations.size());

        ConstraintViolation<MarketGood> fieldLevelViolation = violations.stream()
                .filter(violation -> !violation.getConstraintDescriptor().getPayload().contains(ValidationLevel.MUST.class))
                .findFirst().get();
        ConstraintDescriptor<?> fieldDescriptor = fieldLevelViolation.getConstraintDescriptor();

        ConstraintViolation<MarketGood> classLevelViolation = violations.stream()
                .filter(violation -> violation.getConstraintDescriptor().getPayload().contains(ValidationLevel.MUST.class))
                .findFirst().get();
        ConstraintDescriptor<?> classDescriptor = classLevelViolation.getConstraintDescriptor();

        System.out.println();

        // This is NOT a distinction between class and field levels
        // just number of classes in @Constraint validatedBy
        Assertions.assertEquals(0, fieldDescriptor.getConstraintValidatorClasses().size());
        Assertions.assertEquals(1, classDescriptor.getConstraintValidatorClasses().size());

        // Get payload of validation level
        // Apparently on class-level constraint it's applied to all violations
        Assertions.assertEquals(0, fieldDescriptor.getPayload().size());
        Assertions.assertEquals(1, classDescriptor.getPayload().size());
        Assertions.assertEquals(ValidationLevel.MUST.class, classDescriptor.getPayload().stream().findFirst().get());

        Assertions.assertEquals(Default.class, fieldDescriptor.getGroups().stream().findFirst().get());


    }

}
