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
    public Map<String, Boolean> updateMember(Member updatedMember) {
        // 먼저 데이터베이스에서 기존의 회원 정보를 조회
        // 만약 해당 ID의 회원 정보가 존재하지 않으면 BusinessLogicException을 발생
        Member existingMember = memberRepository.findById(updatedMember.getMemberId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        // 받아온 Member 객체의 각 필드가 null인지 확인
        // 값이 제공된 경우, 기존 회원 정보의 Email을 새로운 Email로 업데이트 (나머지 데이터도 동일한 코드)
        if (updatedMember.getEmail() != null) {
            existingMember.setEmail(updatedMember.getEmail());
        }
        if (updatedMember.getUsername() != null) {
            existingMember.setUsername(updatedMember.getUsername());
        }
        if (updatedMember.getNickname() != null) {
            existingMember.setNickname(updatedMember.getNickname());
        }
        if (updatedMember.getPassword() != null) {
            // 비밀번호 암호화 후 저장
            String hashedPassword = passwordEncoder.encode(updatedMember.getPassword());
            existingMember.setPassword(hashedPassword);
        }

        // 수정된 정보로 회원 정보를 업데이트
        memberRepository.save(existingMember);

        Map<String, Boolean> response = new HashMap<>();
        response.put("updated", true);
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

    @Transactional
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElse(null);
    }
}
