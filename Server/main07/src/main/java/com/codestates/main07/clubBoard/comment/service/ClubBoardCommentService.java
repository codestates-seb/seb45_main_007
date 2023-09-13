package com.codestates.main07.clubBoard.comment.service;

import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import com.codestates.main07.clubBoard.comment.repository.ClubBoardCommentRepository;
import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ClubBoardCommentService {
    private final ClubBoardCommentRepository repository;

    public ClubBoardCommentService(ClubBoardCommentRepository repository) {
        this.repository = repository;
    }

    public ClubBoardComment createComment(ClubBoardComment comment) {
        return repository.save(comment);
    }

    public ClubBoardComment updateComment(ClubBoardComment newComment) {
        ClubBoardComment savedComment = findCorrectComment(newComment.getClubBoardCommentId());

        Optional.ofNullable(newComment.getContent())
                .ifPresent(savedComment::setContent);

        savedComment.setModifiedAt(LocalDateTime.now());
        return savedComment;
    }

    public ClubBoardComment findComment(long clubBoardCommentId) {
        return findCorrectComment(clubBoardCommentId);
    }

    public Page<ClubBoardComment> findComments(int page, int size) {
        return repository.findAll(PageRequest.of(page, size,
                Sort.by("clubBoardCommentId").descending()));
    }

    public void deleteComment(long clubBoardCommentId) {
        ClubBoardComment comment = findCorrectComment(clubBoardCommentId);
        repository.delete(comment);
    }

    private ClubBoardComment findCorrectComment(long clubBoardCommentId) {
        Optional<ClubBoardComment> optional = repository.findById(clubBoardCommentId);
        return optional.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}
