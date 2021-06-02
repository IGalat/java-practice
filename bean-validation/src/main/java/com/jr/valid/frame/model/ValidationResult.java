package com.jr.valid.frame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationResult {
    @JsonProperty
    private FailedConstraintsGroup must;
    @JsonProperty
    private FailedConstraintsGroup warn;
    @JsonIgnore
    private FailedConstraintsGroup silent;

    public FailedConstraintsGroup getMust() {
        return must;
    }

    public void setMust(FailedConstraintsGroup must) {
        this.must = must;
    }

    public FailedConstraintsGroup getWarn() {
        return warn;
    }

    public void setWarn(FailedConstraintsGroup warn) {
        this.warn = warn;
    }

    public FailedConstraintsGroup getSilent() {
        return silent;
    }

    public void setSilent(FailedConstraintsGroup silent) {
        this.silent = silent;
    }

    /**
     * Execute before sending this as a response
     */
    public ValidationResult cleanupResponse() {
        warn.getFields().keySet().removeIf(field -> must.getFields().containsKey(field));
        return this;
    }
}
