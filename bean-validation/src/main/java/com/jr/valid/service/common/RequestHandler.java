package com.jr.valid.service.common;

public interface RequestHandler<I, O> {
    O handleRequest(I input);
}