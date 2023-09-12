package com.codestates.main07.member.service;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // 생성자가 하나 이상일 경우, DI를 적용하기 위한 생성자에 반드시 @Autowired 애너테이션을 붙여야 한다
        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Transactional
        public Member createMember(Member member) {
            if (memberRepository.existsById(member.getMemberId())) {
                throw new BusinessLogicException(ExceptionCode.EMAIL_ALREADY_EXISTS);
            }

            // 비밀번호 해싱
            String hashedPassword = passwordEncoder.encode(member.getPassword());
            member.setPassword(hashedPassword);

            return memberRepository.save(member);
        }

    @Transactional
    public Member updateMember(Member member) {
        if (!memberRepository.existsById(member.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member viewMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (!optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return optionalMember.get();
    }

    @Transactional(readOnly = true)
    public List<Member> viewMembers() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return members;
    }

    @Transactional
    public void deleteMember(long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        memberRepository.deleteById(memberId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
