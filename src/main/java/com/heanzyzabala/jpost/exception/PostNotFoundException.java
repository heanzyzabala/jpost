package com.heanzyzabala.jpost.exception;

import java.util.UUID;

import static java.lang.String.format;

public class PostNotFoundException extends RuntimeException {
    private static final String CODE = "PNFE";

    public PostNotFoundException(UUID id) {
        super(format("Post with id %s not found", id));
    }

    public String getCode() {
        return CODE;
    }
}
