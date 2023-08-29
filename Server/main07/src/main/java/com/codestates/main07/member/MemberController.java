package com.codestates.main07.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("username") String username,
                                     @RequestParam("nickname") String nickname,
                                     @RequestParam("password") String password) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", username);
        map.put("phone", nickname);
        map.put("password", password);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
