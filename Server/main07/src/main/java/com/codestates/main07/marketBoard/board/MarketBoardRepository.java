package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.domain.MarketBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketBoardRepository extends JpaRepository<MarketBoard, Long> {
}
