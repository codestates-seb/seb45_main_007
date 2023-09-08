package com.codestates.main07.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Size(min = 8, max = 16, message = "8자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9\\W]).*$", message = "영문, 숫자, 특수문자 중 하나 이상을 포함해야 합니다.")
    private String password;
}
