package com.codestates.main07.member.controller;

import com.codestates.main07.member.dto.MemberResponseDto;
import com.codestates.main07.member.dto.MemberUpdateDto;
import com.codestates.main07.member.dto.MemberPostDto;
import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.mapper.MemberMapper;
import com.codestates.main07.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    // DI : MemberController 생성자 파라미터로 MemberService의 객체를 주입(Injection)
    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    /**
    반환 타입을 ResponseEntity<?>로 변경하였는데,
    ?는 와일드카드 타입이며, 여러 타입이 가능하다는 것을 나타냅니다.
    이렇게 하면 나중에 다른 타입의 객체를 반환하고 싶을 때도 유연하게 대응할 수 있습니다.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> postMember(@Valid @RequestBody MemberPostDto memberDto) {
        Member member = mapper.memberPostDtoToMember(memberDto);
        Map<String, Boolean> result = memberService.createMember(member);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{member-id}")
    public ResponseEntity<?> updateMember(@PathVariable("member-id") long memberId,
                                      @Valid @RequestBody MemberUpdateDto memberUpdateDto) {
        memberUpdateDto.setMemberId(memberId);
        Map<String, Boolean> result = memberService.updateMember(mapper.memberUpdateDtoToMember(memberUpdateDto));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity viewMember(
            @PathVariable("member-id") long memberId) {
        Member response = memberService.viewMember(memberId);
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity viewMembers() {
        List<Member> members = memberService.viewMembers();

        List<MemberResponseDto> response =
                members.stream()
                        .map(member -> mapper.memberToMemberResponseDto(member))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        Map<String, Boolean> result = memberService.deleteMember(memberId);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}
