package com.jr.valid.model.common;

import com.jr.valid.frame.model.ValidationResult;

// todo validate this on exit from lambda proxy
public class ApiResponse<B extends BaseResponse> {
    private B responseBody;
    private int statusCode = 200;
    private String errorMessage;
    private ValidationResult validationErrors;

    public ApiResponse() {
    }

    public ApiResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public ApiResponse(B responseBody) {
        this.responseBody = responseBody;
    }

    public ApiResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ApiResponse(B responseBody, int statusCode) {
        this.responseBody = responseBody;
        this.statusCode = statusCode;
    }

    public ApiResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public B getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(B responseBody) {
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ValidationResult getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(ValidationResult validationErrors) {
        this.validationErrors = validationErrors;
    }
}
