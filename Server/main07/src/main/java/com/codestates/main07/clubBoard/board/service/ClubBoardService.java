package com.codestates.main07.clubBoard.board.service;

import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import com.codestates.main07.clubBoard.board.repository.ClubBoardRepository;
import com.codestates.main07.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.codestates.main07.exception.BusinessLogicException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ClubBoardService {
    private final ClubBoardRepository repository;

    public ClubBoardService(ClubBoardRepository repository) {
        this.repository = repository;
    }

    public ClubBoard createClubBoard(ClubBoard clubBoard) {
        return repository.save(clubBoard);
    }

    public ClubBoard updateClubBoard(ClubBoard newClubBoard) {
        ClubBoard savedClubBoard = findCorrectClubBoard(newClubBoard.getClubBoardId());

        Optional.ofNullable(newClubBoard.getTitle())
                .ifPresent(savedClubBoard::setTitle);
        Optional.ofNullable(newClubBoard.getContent())
                .ifPresent(savedClubBoard::setContent);
        Optional.ofNullable(newClubBoard.getCategory())
                .ifPresent(savedClubBoard::setCategory);

        savedClubBoard.setModifiedAt(LocalDateTime.now());

        return savedClubBoard;
    }

    public ClubBoard findClubBoard(long clubBoardId) {
        return findCorrectClubBoard(clubBoardId);
    }

    public Page<ClubBoard> findClubBoards(int page, int size) {
        return repository.findAll(PageRequest.of(page, size,
                Sort.by("clubBoardId").descending()));
    }

    public void deleteClubBoard(long clubBoardId) {
        ClubBoard clubBoard = findCorrectClubBoard(clubBoardId);
        repository.delete(clubBoard);
    }

    private ClubBoard findCorrectClubBoard(long clubBoardId) {
        Optional<ClubBoard> optional = repository.findById(clubBoardId);
        return optional.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public void plusViewCount(ClubBoard clubBoard) {
        clubBoard.setViewCount(clubBoard.getViewCount() + 1);
    }
}
