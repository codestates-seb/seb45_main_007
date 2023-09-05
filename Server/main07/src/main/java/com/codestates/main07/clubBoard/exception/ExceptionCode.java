package com.codestates.main07.clubBoard.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    BOARD_NOT_FOUND(404, "Board not found"),
    COMMENT_NOT_FOUND(404, "Comment not found");

    private int status;

    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
