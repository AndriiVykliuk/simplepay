package com.simple.pay.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ServiceException extends RuntimeException {

    private final int code;

    private final Map<String, Object> messages;

    public ServiceException(int code, Map<String, Object> messages) {
        this.code = code;
        this.messages = messages;
    }

    public ServiceException(int code, String topic, Object reason) {
        this.code = code;
        messages = Map.of(topic, reason);
    }

}
