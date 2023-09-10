package com.codestates.main07.clubBoardRecommend.repository;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRespository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
}
