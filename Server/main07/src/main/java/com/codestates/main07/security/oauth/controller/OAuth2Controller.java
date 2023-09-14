package com.codestates.main07.security.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    @GetMapping("/auth/google/login")
    public String redirectToGoogle() {
        return "redirect:/oauth2/authorization/google";  // Google의 OAuth2 로그인 페이지로 리다이렉트
    }

    @GetMapping("/auth/kakao/login")
    public String redirectToKakao() {
        return "redirect:/oauth2/authorization/kakao";   // Kakao의 OAuth2 로그인 페이지로 리다이렉트
    }
}
