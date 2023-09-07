package com.codestates.main07.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberPostDto {
    @Email
    private String email;
    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String username;
    @NotBlank(message = "닉네임은 공백이 아니어야 합니다.")
    private String nickname;
    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    private String password;
}
