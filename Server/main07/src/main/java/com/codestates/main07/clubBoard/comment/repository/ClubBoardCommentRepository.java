package com.codestates.main07.clubBoard.comment.repository;

import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubBoardCommentRepository extends JpaRepository<ClubBoardComment, Long> {
    Page<ClubBoardComment> findByClubBoard_ClubBoardId(Pageable pageable, long clubBoardId);
}
