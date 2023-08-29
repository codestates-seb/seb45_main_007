package com.codestates.main07.search.exception;

import lombok.Getter;

public enum ExceptionCode {
    SEARCH_NOT_FOUND(404, "Search not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
