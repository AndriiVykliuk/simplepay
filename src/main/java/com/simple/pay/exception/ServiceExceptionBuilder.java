package com.simple.pay.exception;

import java.util.HashMap;
import java.util.Map;

public class ServiceExceptionBuilder {

    private int code;

    private Map<String, Object> errors = new HashMap<>();

    public ServiceExceptionBuilder withCode(int code) {
        this.code = code;
        return this;
    }

    public ServiceExceptionBuilder withError(String topic, Object reason) {
        errors.put(topic, reason);
        return this;
    }

    public ServiceException build() {
        return new ServiceException(code, errors);
    }

}
