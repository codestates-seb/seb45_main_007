package com.codestates.main07.marketBoard.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketBoardCommentRepository extends JpaRepository<MarketBoardComment, Long> {
}
