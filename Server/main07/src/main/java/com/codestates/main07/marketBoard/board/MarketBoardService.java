package com.codestates.main07.marketBoard.board;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class MarketBoardService {
    private final MarketBoardRepository marketBoardRepository;

    @Autowired
    public MarketBoardService(MarketBoardRepository marketBoardRepository) {
        this.marketBoardRepository = marketBoardRepository;
    }

    public MarketBoard createBoard(MarketBoard marketBoard) {
        return marketBoardRepository.save(marketBoard);
    }

    public MarketBoard updateBoard(MarketBoard marketBoard) {
        MarketBoard findMarketBoard = findCorrectMarketBoard(marketBoard.getMarketBoardId());

        findMarketBoard.update(marketBoard.getTitle(), marketBoard.getContent());

//        findMarketBoard.setModifiedAt(LocalDateTime.now());

        return marketBoardRepository.save(findMarketBoard);
    }

    public void deleteBoard (long marketBoardId) {
        MarketBoard findMarketBoard = findCorrectMarketBoard(marketBoardId);
        marketBoardRepository.delete(findMarketBoard);
    }

    @Transactional(readOnly = true)
    public MarketBoard viewBoard(long marketBoardId) {
        return findCorrectMarketBoard(marketBoardId);
    }

    public Page<MarketBoard> boardList(int page, int size) {
        return marketBoardRepository.findAll(PageRequest.of(
                page, size, Sort.by("createdAt").descending()));
    }

    private MarketBoard findCorrectMarketBoard(long marketBoardId) {
        Optional<MarketBoard> optionalMarketBoard = marketBoardRepository.findById(marketBoardId);
        return optionalMarketBoard.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }
}
