package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.domain.MarketBoard;
import com.codestates.main07.marketBoard.board.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketBoardRepository extends JpaRepository<MarketBoard, Long> {
    Page<MarketBoard> findByMember_MemberId(Pageable pageable, long memberId);
}
