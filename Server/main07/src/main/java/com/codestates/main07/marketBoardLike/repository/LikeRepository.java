package com.codestates.main07.marketBoardLike.repository;

import com.codestates.main07.marketBoard.board.MarketBoard;
import com.codestates.main07.marketBoardLike.entity.Likes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByMarketBoard_MarketBoardIdAndMember_MemberId(long marketBoardId, long memberId);
    boolean existsByMarketBoard_MarketBoardIdAndMember_MemberId(long marketBoardId, long memberId);
}
