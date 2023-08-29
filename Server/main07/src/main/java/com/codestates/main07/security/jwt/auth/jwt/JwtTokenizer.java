package com.codestates.main07.security.jwt.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenizer {
    // (2), (3), (4)는 JWT 생성 시 필요한 정보이며, 해당 정보는 application.yml 파일에서 로드
    @Getter
    @Value("${jwt.key}")
    private String secretKey;       // (2) JWT 생성 및 검증 시 사용되는 Secret Key 정보

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;        // (3) Access Token에 대한 만료 시간 정보

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;          // (4)  Refresh Token에 대한 만료 시간 정보

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 인증된 사용자에게 JWT를 최초로 발급해 주기 위한 JWT 생성 메서드
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey,
                                      Long memberId,
                                      String email,
                                      String username) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey); // Base64 형식 Secret Key 문자열을 이용해 Key(java.security.Key) 객체를 얻습니다.

        // 클레임에 멤버 ID와 이메일을 추가합니다.
        claims.put("memberId", memberId);
        claims.put("email", email);
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims) // 주로 인증된 사용자와 관련된 정보를 추가
                .setSubject(subject) // 제목 추가
                .setIssuedAt(Calendar.getInstance().getTime()) // 발행 일자
                .setExpiration(expiration) // 만료 일시
                .signWith(key) // 서명을 위한 Key(java.security.Key) 객체를 설정
                .compact(); // JWT 생성 + 직렬화
    }

    // Access Token이 만료되었을 경우, Access Token을 새로 생성
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }

    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    // (5) JWT의 만료 일시를 지정하기 위한 메서드로 JWT 생성 시 사용
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    // JWT의 서명에 사용할 Secret Key를 생성
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        // Decoders.BASE64.decode() 메서드는 Base64 형식으로 인코딩 된 Secret Key를 디코딩한 후, byte array를 반환
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        // Keys.hmacShaKeyFor() 메서드는 key byte array를 기반으로 적절한 HMAC 알고리즘을 적용한 Key(java.security.Key) 객체를 생성
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }
}
