package com.simple.pay.exception;

import lombok.Getter;

import java.util.Map;

/**
 * Business exception, thrown by service logic.
 */
@Getter
public class ServiceException extends RuntimeException {

    /**
     * Exception code. To simplify logic (and because of a short development time-ranges) this is HTTP response code.
     */
    private final int code;

    /**
     * Detailed error information.
     */
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
