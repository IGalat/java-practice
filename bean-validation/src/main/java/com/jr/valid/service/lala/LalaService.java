package com.jr.valid.service.lala;

import com.jr.valid.model.SomeAlsoModel;
import com.jr.valid.model.SomeModel;
import com.jr.valid.model.common.ApiRequest;
import com.jr.valid.model.common.ApiResponse;
import com.jr.valid.model.common.ExampleResponse;
import com.jr.valid.service.common.LambdaProxySim;

public class LalaService extends LambdaProxySim<SomeModel, ExampleResponse> {

    @Override
    protected Class<?> validateAgainst(ApiRequest<SomeModel> request) {
        return SomeAlsoModel.class;
    }

    @Override
    public ApiResponse<ExampleResponse> handleByLambda(ApiRequest<SomeModel> request) {
        return null;
    }
}
