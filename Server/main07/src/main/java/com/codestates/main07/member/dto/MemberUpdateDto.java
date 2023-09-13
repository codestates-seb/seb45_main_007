package com.codestates.main07.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class MemberUpdateDto {
    private long memberId;
    @Email
    private String email;
    private String username;
    private String nickname;
    private String password;
}
