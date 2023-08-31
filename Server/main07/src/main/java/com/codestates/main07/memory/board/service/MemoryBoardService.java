package com.codestates.main07.memory.board.service;

import com.codestates.main07.memory.board.entity.MemoryBoard;
import com.codestates.main07.memory.board.repository.MemoryBoardRepository;
import com.codestates.main07.memory.exception.BusinessLogicException;
import com.codestates.main07.memory.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemoryBoardService {
    private final MemoryBoardRepository repository;

    public MemoryBoardService(MemoryBoardRepository repository) {
        this.repository = repository;
    }

    public MemoryBoard createMemoryBoard(MemoryBoard memoryBoard) {
        return repository.save(memoryBoard);
    }

    public MemoryBoard updateMemoryBoard(MemoryBoard newMemoryBoard) {
        MemoryBoard oldMemoryBoard = findCorrectMemoryBoard(newMemoryBoard.getMemoryBoardId());

        Optional.ofNullable(newMemoryBoard.getTitle())
                .ifPresent(oldMemoryBoard::setTitle);
        Optional.ofNullable(newMemoryBoard.getContent())
                .ifPresent(oldMemoryBoard::setContent);
        Optional.ofNullable(newMemoryBoard.getCategory())
                .ifPresent(oldMemoryBoard::setCategory);

        oldMemoryBoard.setModifiedAt(LocalDateTime.now());

        return oldMemoryBoard;
    }

    public MemoryBoard findMemoryBoard(long memoryBoardId) {
        return findCorrectMemoryBoard(memoryBoardId);
    }

    public Page<MemoryBoard> findMemoryBoards(int page, int size) {
        return repository.findAll(PageRequest.of(page, size,
                Sort.by("memoryBoardId").descending()));
    }

    public void deleteMemoryBoard(long memoryBoardId) {
        MemoryBoard memoryBoard = findCorrectMemoryBoard(memoryBoardId);
        repository.delete(memoryBoard);
    }

    private MemoryBoard findCorrectMemoryBoard(long memoryBoardId) {
        Optional<MemoryBoard> optional = repository.findById(memoryBoardId);
        return optional.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMORYBOARD_NOT_FOUND));
    }
}
