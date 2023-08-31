package com.codestates.main07.buysellBoard.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuysellBoardRepository extends JpaRepository<BuysellBoard, Long> {
}
