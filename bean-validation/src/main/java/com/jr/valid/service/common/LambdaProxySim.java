package com.jr.valid.service.common;

import com.jr.valid.frame.DataValidator;
import com.jr.valid.model.common.ApiRequest;
import com.jr.valid.model.common.ApiResponse;
import com.jr.valid.model.common.BaseResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public abstract class LambdaProxySim<I, O extends BaseResponse> implements RequestHandler<ApiRequest<I>, ApiResponse<O>> {

    protected void onStart(ApiRequest<I> request) {
    }

    protected void onComplete(ApiRequest<I> request) {
    }

    protected boolean dropInvalidFields(ApiRequest<I> request) {
        return true;
    }

    protected Class<?> validateAgainst(ApiRequest<I> request) {
        return null;
    }

    protected Set<Class<?>> validateGroups(ApiRequest<I> request) { return null; }

    @Override
    public ApiResponse<O> handleRequest(ApiRequest<I> request) {
        try {
            DataValidator.getInstance().validate(request, validateAgainst(request), dropInvalidFields(request), validateGroups(request));
        } catch (IOException e) {
            ApiResponse<O> response = new ApiResponse<>(500, "Validator internal error: " + e.getMessage());
        }
        //todo if any MUST fails, 400
        onStart(request);
        ApiResponse<O> response = handleByLambda(request);
        onComplete(request);
        return response;
    }

    public abstract ApiResponse<O> handleByLambda(ApiRequest<I> request);
}
