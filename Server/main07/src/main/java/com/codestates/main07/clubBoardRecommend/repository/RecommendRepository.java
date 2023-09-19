package com.codestates.main07.clubBoardRecommend.repository;

import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    List<Recommend> findByClubBoard_ClubBoardIdAndMember_MemberId(long clubBoardId, long memberId);
    long countByClubBoard_ClubBoardId(long clubBoardId);
    boolean existsByClubBoard_ClubBoardIdAndMember_MemberId(long clubBoardId, long memberId);
}
