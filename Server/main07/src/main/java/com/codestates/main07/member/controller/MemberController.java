package com.codestates.main07.member.controller;

import com.codestates.main07.member.memberdto.MemberPatchDto;
import com.codestates.main07.member.memberdto.MemberPostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@Valid MemberPostDto memberDto) {
        return new ResponseEntity<MemberPostDto>(memberDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setNickname(memberPatchDto.getNickname());
        memberPatchDto.setEmail(memberPatchDto.getEmail());
        memberPatchDto.setUsername(memberPatchDto.getUsername());
        memberPatchDto.setPassword(memberPatchDto.getPassword());

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }
}
