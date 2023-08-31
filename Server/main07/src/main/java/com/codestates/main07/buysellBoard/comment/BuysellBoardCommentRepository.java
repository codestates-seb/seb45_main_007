package com.codestates.main07.buysellBoard.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuysellBoardCommentRepository extends JpaRepository<BuysellBoardComment, Long> {
}
