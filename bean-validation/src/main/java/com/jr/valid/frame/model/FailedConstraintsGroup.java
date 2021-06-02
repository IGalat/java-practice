package com.jr.valid.frame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class FailedConstraintsGroup {
    @JsonProperty
    private String description;
    @JsonProperty
    private Map<String, String> fields;
    @JsonProperty
    private List<String> other;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public List<String> getOther() {
        return other;
    }

    public void setOther(List<String> other) {
        this.other = other;
    }

    public boolean isEmpty() {
        return fields.isEmpty() && other.isEmpty();
    }

    @Override
    public String toString() {
        return "FailedConstraintsGroup{" +
                ", description='" + description + '\'' +
                ", fields=" + fields +
                ", other=" + other +
                '}';
    }
}
