package com.codestates.main07.memoryBoard.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    MEMORY_BOARD_NOT_FOUND(404, "Memory board not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    COMMENT_NOT_FOUND(404, "Comment not found");

    private int status;

    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
