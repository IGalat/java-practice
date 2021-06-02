package com.jr.valid.model.common;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jr.valid.frame.model.FailedConstraintsGroup;

public class BaseResponse {
    @JsonProperty
    private FailedConstraintsGroup validationWarning;

    public FailedConstraintsGroup getValidationWarning() {
        return validationWarning;
    }

    public void setValidationWarning(FailedConstraintsGroup validationWarning) {
        this.validationWarning = validationWarning;
    }
}
