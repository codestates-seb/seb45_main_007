package com.codestates.main07.exception;

import lombok.Getter;

public enum ExceptionCode {
    // 일반적인 상황
    MEMBER_NOT_FOUND(404, "회원을 찾을 수 없습니다"),
    INVALID_REQUEST(400, "잘못된 요청입니다"),

    // 회원 가입과 관련된 예외
    EMAIL_ALREADY_EXISTS(409, "이미 존재하는 이메일입니다"),
    NICKNAME_ALREADY_EXISTS(409, "이미 존재하는 닉네임입니다"),
    SIGNUP_FAILED(500, "회원 가입에 실패하였습니다. 나중에 다시 시도해주세요"),

    // 회원 정보 업데이트와 관련된 예외
    UPDATE_FAILED(500, "회원 정보 수정에 실패하였습니다"),
    PASSWORD_MISMATCH(400, "현재 비밀번호가 일치하지 않습니다"),

    // 회원 정보 조회와 관련된 예외
    UNAUTHORIZED_ACCESS(401, "권한이 없는 접근입니다"),

    // 회원 탈퇴나 정보 삭제와 관련된 예외
    DELETE_FAILED(500, "회원 정보 삭제에 실패하였습니다"),
    UNABLE_TO_WITHDRAW(500, "회원 탈퇴를 진행할 수 없습니다"),

    // 알 수 없거나 예측하지 못한 에러
    UNKNOWN_ERROR(500, "알 수 없는 오류가 발생하였습니다");

    // 메모리 예외

    // 바이셀 예외

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
