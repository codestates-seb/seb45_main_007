package com.codestates.main07.member.service;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // 생성자가 하나 이상일 경우, DI를 적용하기 위한 생성자에 반드시 @Autowired 애너테이션을 붙여야 한다
        @Autowired
        private MemberRepository memberRepository;

        public Member createMember(Member member) {
            return memberRepository.save(member);
        }

    public Member updateMember(Member member) {
        // 기존 데이터가 있는지 확인
        if (memberRepository.existsById(member.getMemberId())) {
            return memberRepository.save(member);  // 있으면 업데이트
        }
        // TODO 없으면 예외 처리 or 다른 로직 필요
        return null;
    }

    public Member findMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        return optionalMember.orElse(null);  // TODO 찾지 못하면 null 반환 (또는 예외 처리)
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        memberRepository.deleteById(memberId);
    }
}
