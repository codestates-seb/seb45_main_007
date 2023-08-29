package com.codestates.main07.member.memberdto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class MemberPatchDto {
    private long memberId;
    @Email
    private String email;
    private String username;
    private String nickname;
    private String password;
}
