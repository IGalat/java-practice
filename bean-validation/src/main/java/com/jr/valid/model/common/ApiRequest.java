package com.jr.valid.model.common;

public class ApiRequest<R> {
    private R body;
    private ApiMethod method;

    public R getBody() {
        return body;
    }

    public void setBody(R body) {
        this.body = body;
    }

    public ApiMethod getMethod() {
        return method;
    }

    public void setMethod(ApiMethod method) {
        this.method = method;
    }
}
