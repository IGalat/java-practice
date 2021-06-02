package com.jr.valid.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Databind {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
