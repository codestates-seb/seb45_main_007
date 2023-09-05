package com.codestates.main07.clubBoard.comment.repository;

import com.codestates.main07.clubBoard.comment.entity.MemoryBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryBoardCommentRepository extends JpaRepository<MemoryBoardComment, Long> {
}
