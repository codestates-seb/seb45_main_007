package com.codestates.main07.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성
@AllArgsConstructor // 멤버 클래스의 모든 멤버 변수를 파라미터로 갖는 멤버 생성자를 자동 생성
public class Member {
    private long memberId;
    private String email;
    private String username;
    private String nickname;
    private String password;
}
