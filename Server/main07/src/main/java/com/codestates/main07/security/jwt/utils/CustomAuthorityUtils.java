package com.codestates.main07.security.jwt.utils;

import com.codestates.main07.member.entity.Member;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthorityUtils {
    @Value("${mail.address.admin}")
    private String adminMailAddress;

    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");
    private final List<GrantedAuthority> OAUTH2_USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_OAUTH2_USER", "ROLE_USER");
    private final List<String> OAUTH2_USER_ROLES_STRING = List.of("OAUTH2_USER", "USER");



    // 메모리 상의 Role을 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthorities(String email, boolean isOAuth2User) {
        List<GrantedAuthority> authorities;
        if (email.equals(adminMailAddress)) {
            authorities = ADMIN_ROLES;
        } else if (isOAuth2User) {
            authorities = OAUTH2_USER_ROLES;
        } else {
            authorities = USER_ROLES_STRING.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        }
        return authorities;
    }

    // DB에 저장된 Role을 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthoritiesFromRoles(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    // Member 객체를 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthorities(Member member) {
        List<GrantedAuthority> authorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    // DB 저장 용
    public List<String> createRoles(String email, boolean isOAuth2User) {
        if (email.equals(adminMailAddress)) {
            return ADMIN_ROLES_STRING;
        } else if (isOAuth2User) {
            return OAUTH2_USER_ROLES_STRING;
        }
        return USER_ROLES_STRING;
    }
}
