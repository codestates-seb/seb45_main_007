package com.codestates.main07.member.service;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {

    // 생성자가 하나 이상일 경우, DI를 적용하기 위한 생성자에 반드시 @Autowired 애너테이션을 붙여야 한다
        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Transactional
        public Map<String, Boolean> createMember(Member member) {
            if (memberRepository.existsByEmail(member.getEmail())) {
                throw new BusinessLogicException(ExceptionCode.EMAIL_ALREADY_EXISTS);
            }

            // 닉네임 중복 검사
            if (memberRepository.findByNickname(member.getNickname()).isPresent()) {
                throw new BusinessLogicException(ExceptionCode.NICKNAME_ALREADY_EXISTS);
            }

            // 비밀번호 해싱
            String hashedPassword = passwordEncoder.encode(member.getPassword());
            member.setPassword(hashedPassword);
            memberRepository.save(member);

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", true);
            return response;
        }

    @Transactional
    public Map<String, Boolean> updateMember(Member member) {
        if (!memberRepository.existsById(member.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        memberRepository.save(member);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return response;
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
    public Map<String, Boolean> deleteMember(long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        memberRepository.deleteById(memberId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}
