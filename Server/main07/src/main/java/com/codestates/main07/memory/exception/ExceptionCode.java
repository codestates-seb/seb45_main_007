package com.codestates.main07.memory.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMORYBOARD_NOT_FOUND(404, "Memory board not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
