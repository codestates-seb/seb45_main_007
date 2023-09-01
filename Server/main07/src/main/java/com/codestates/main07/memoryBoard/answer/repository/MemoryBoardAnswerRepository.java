package com.codestates.main07.memoryBoard.answer.repository;

import com.codestates.main07.memoryBoard.answer.entity.MemoryBoardAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryBoardAnswerRepository extends JpaRepository<MemoryBoardAnswer, Long> {
    List<MemoryBoardAnswer> findByMemoryBoardIdOrderByMemoryBoardAnswerIdDesc(long memoryBoardId);
}
