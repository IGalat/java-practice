package com.jr.valid.frame;

import com.jr.valid.frame.model.FailedConstraintsGroup;
import com.jr.valid.frame.model.ValidationResult;
import jakarta.validation.groups.Default;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DataValidator {
    private static final DataValidator instance = new DataValidator();

    public static DataValidator getInstance() {
        return instance;
    }

    public <I> ValidationResult validate(I input, Class<?> validateAgainst, boolean dropInvalid, Set<Class<?>> groups) throws IOException {
        if (validateAgainst == null)
            validateAgainst = input.getClass();
        if (!validateAgainst.isInstance(input.getClass())) {
            throw new IOException("LOGIC ERROR: validateAgainst class is not a subclass of request class");
        }
        groups = correctGroups(groups);
        ValidationResult result = createValidationResult(dropInvalid);


        //todo
        return new ValidationResult();
    }

    private Set<Class<?>> correctGroups(Set<Class<?>> groups) {
        if (groups == null)
            groups = new HashSet<>();
        HashSet<Class<?>> result = new HashSet<>(groups);
        result.add(Default.class);
        return result;
    }

    private ValidationResult createValidationResult(boolean dropInvalid) {
        ValidationResult result = new ValidationResult();
        FailedConstraintsGroup must = new FailedConstraintsGroup();
        must.setDescription("Critical constraint failures, cannot proceed");
        result.setMust(must);

        FailedConstraintsGroup warn = new FailedConstraintsGroup();
        if (dropInvalid) {
            warn.setDescription("Non-critical constraint failures, will drop invalid values and proceed");
        } else {
            warn.setDescription("Non-critical constraint failures, will proceed");
        }
        result.setWarn(warn);

        FailedConstraintsGroup silent = new FailedConstraintsGroup();
        silent.setDescription("SHOULD NOT BE PRESENT IN RESPONSE");
        result.setSilent(silent);
        return result;
    }
}
