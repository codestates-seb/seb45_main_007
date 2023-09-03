package com.codestates.main07.memoryBoard.comment.service;

import com.codestates.main07.memoryBoard.comment.entity.MemoryBoardComment;
import com.codestates.main07.memoryBoard.comment.repository.MemoryBoardCommentRepository;
import com.codestates.main07.memoryBoard.exception.BusinessLogicException;
import com.codestates.main07.memoryBoard.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemoryBoardCommentService {
    private final MemoryBoardCommentRepository repository;

    public MemoryBoardCommentService(MemoryBoardCommentRepository repository) {
        this.repository = repository;
    }

    public MemoryBoardComment createComment(MemoryBoardComment comment) {
        return repository.save(comment);
    }

    public MemoryBoardComment updateComment(MemoryBoardComment newComment) {
        MemoryBoardComment savedComment = findCorrectComment(newComment.getMemoryBoardCommentId());

        Optional.ofNullable(newComment.getContent())
                .ifPresent(savedComment::setContent);

        savedComment.setModifiedAt(LocalDateTime.now());
        return savedComment;
    }

    public MemoryBoardComment findComment(long memoryBoardCommentId) {
        return findCorrectComment(memoryBoardCommentId);
    }

    public Page<MemoryBoardComment> findComments(int page, int size) {
        return repository.findAll(PageRequest.of(page, size,
                Sort.by("memoryBoardCommentId").descending()));
    }

    public void deleteComment(long memoryBoardCommentId) {
        MemoryBoardComment comment = findCorrectComment(memoryBoardCommentId);
        repository.delete(comment);
    }

    private MemoryBoardComment findCorrectComment(long memoryBoardCommentId) {
        Optional<MemoryBoardComment> optional = repository.findById(memoryBoardCommentId);
        return optional.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}
