package com.codestates.main07.memoryBoard.answer.service;

import com.codestates.main07.memoryBoard.answer.entity.MemoryBoardAnswer;
import com.codestates.main07.memoryBoard.answer.repository.MemoryBoardAnswerRepository;
import com.codestates.main07.memoryBoard.exception.BusinessLogicException;
import com.codestates.main07.memoryBoard.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemoryBoardAnswerService {
    private final MemoryBoardAnswerRepository repository;

    public MemoryBoardAnswerService(MemoryBoardAnswerRepository repository) {
        this.repository = repository;
    }

    public MemoryBoardAnswer createAnswer(MemoryBoardAnswer answer) {
        return repository.save(answer);
    }

    public MemoryBoardAnswer updateAnswer(MemoryBoardAnswer newAnswer) {
        MemoryBoardAnswer savedAnswer = findCorrectAnswer(newAnswer.getMemoryBoardAnswerId());

        Optional.ofNullable(newAnswer.getContent())
                .ifPresent(savedAnswer::setContent);

        savedAnswer.setModifiedAt(LocalDateTime.now());
        return savedAnswer;
    }

    public MemoryBoardAnswer findAnswer(long memoryBoardAnswerId) {
        return findCorrectAnswer(memoryBoardAnswerId);
    }

    public List<MemoryBoardAnswer> findAnswers(long memoryBoardId) {
        return repository.findByMemoryBoardIdOrderByMemoryBoardAnswerIdDesc(memoryBoardId);
    }

    public void deleteAnswer(long memoryBoardAnswerId) {
        MemoryBoardAnswer answer = findCorrectAnswer(memoryBoardAnswerId);
        repository.delete(answer);
    }

    private MemoryBoardAnswer findCorrectAnswer(long memoryBoardAnswerId) {
        Optional<MemoryBoardAnswer> optional = repository.findById(memoryBoardAnswerId);
        return optional.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }
}
