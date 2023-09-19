package com.codestates.main07.security.jwt.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256PasswordEncoder implements PasswordEncoder {
    // 비밀번호를 SHA-256 알고리즘을 사용하여 해싱하는 메서드
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            // SHA-256 알고리즘의 MessageDigest 객체를 초기화
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 원본 비밀번호를 byte 배열로 변환한 후 SHA-256 알고리즘을 사용하여 해싱
            byte[] encodedhash = digest.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
            // byte 배열을 16진수 문자열로 변환하기 위한 StringBuilder를 초기화
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                hexString.append(String.format("%02x", b));
            }
            // 최종적으로 변환된 16진수 문자열을 반환
            return hexString.toString();
        } catch (Exception e) {
            // 예외 발생 시 RuntimeException
            throw new RuntimeException(e);
        }
    }

    // 원본 비밀번호와 해싱된 비밀번호를 비교 - 일치하면 true
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
