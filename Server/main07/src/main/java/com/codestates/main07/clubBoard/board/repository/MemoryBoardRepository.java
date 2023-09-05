package com.codestates.main07.clubBoard.board.repository;

import com.codestates.main07.clubBoard.board.entity.MemoryBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryBoardRepository extends JpaRepository<MemoryBoard, Long> {
}
