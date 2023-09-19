package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.comment.MarketBoardComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketBoardCommentRepository extends JpaRepository<MarketBoardComment, Long> {
    Page<MarketBoardComment> findByMarketBoard_MarketBoardId(Pageable pageable, long marketBoardId);
}
