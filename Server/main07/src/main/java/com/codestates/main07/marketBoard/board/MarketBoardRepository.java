package com.codestates.main07.marketBoard.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketBoardRepository extends JpaRepository<MarketBoard, Long> {
}
