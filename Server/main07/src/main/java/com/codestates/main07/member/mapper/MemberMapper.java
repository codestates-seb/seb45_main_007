package com.codestates.main07.member.mapper;

import com.codestates.main07.member.dto.MemberPostDto;
import com.codestates.main07.member.dto.MemberResponseDto;
import com.codestates.main07.member.dto.MemberUpdateDto;
import com.codestates.main07.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Spring의 Bean으로 등록
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberUpdateDtoToMember(MemberUpdateDto memberUpdateDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}