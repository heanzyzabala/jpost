package com.heanzyzabala.jpost.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {
    private Map<String, Object> error = new HashMap<>();

    public ErrorResponse(String code, String message) {
        error.put("code", code);
        error.put("message", message);
    }
}
