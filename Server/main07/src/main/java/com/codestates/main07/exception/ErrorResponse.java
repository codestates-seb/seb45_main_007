package com.codestates.main07.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private int statusCode; // HTTP 응답 상태 코드를 나타내는 변수
    private String message; // 응답 메시지를 나타내는 변수
    private List<FieldError> fieldErrors; // DTO 멤버 변수 필드의 유효성 검증 실패로 발생한 에러 정보를 담는 멤버 변수
    private List<ConstraintViolationError> violationErrors;  // URI 변수 값의 유효성 검증에 실패로 발생한 에러 정보를 담는 멤버 변수


    public ErrorResponse(int statusCode, String message, List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
        this.statusCode = statusCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    // HTTP 상태 코드와 메시지로 ErrorResponse 객체 생성
    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), null, null);
    }

    // BindingResult에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(BindingResult bindingResult) {
        List<FieldError> fieldErrors = FieldError.of(bindingResult);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), fieldErrors, null);
    }

    // Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        List<ConstraintViolationError> violationErrors = ConstraintViolationError.of(violations);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, violationErrors);
    }

    // DTO 클래스의 멤버 변수의 유효성 검증에서 발생하는 에러 정보를 생성
    @Getter
    public static class FieldError {
        private String field; // DTO 멤버 변수 이름
        private Object rejectedValue; // 유효성 검증 실패한 값
        private String reason; // 실패 이유 메세지

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        /**
         * of() 메서드는 네이밍 컨벤션이며, 객체 생성 시 어떤 값들의 객체를 생성한다는 의미에서 of() 메서드를 사용한다.
         */

        // BindingResult에 대한 FieldError 리스트 생성
        public static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ?
                                    "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    // URI 변수 값에 대한 에러 정보를 생성
    @Getter
    public static class ConstraintViolationError {
        private String propertyPath; // URI 변수 경로
        private Object rejectedValue; // 유효성 검증 실패한 값
        private String reason; // 실패 이유 메시지

        private ConstraintViolationError(String propertyPath, Object rejectedValue,
                                         String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        // Set<ConstraintViolation<?>> 객체에 대한 ConstraintViolationError 리스트 생성
        public static List<ConstraintViolationError> of(
                Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()
                    )).collect(Collectors.toList());
        }
    }
}
