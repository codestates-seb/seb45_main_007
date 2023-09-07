package com.codestates.main07.clubBoard.board.repository;

import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long> {
    Page<ClubBoard> findByTitleContaining(Pageable pageable, String keyword);
}
