package com.codestates.main07.member.repository;

import com.codestates.main07.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email); // Email 중복 확인 위함
    Optional<Member> findByNickname(String nickname); // Nickname 중복 확인 위함

    boolean existsByEmail(String email);

}
