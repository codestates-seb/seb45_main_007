package com.codestates.main07.marketBoard.board;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import com.codestates.main07.marketBoard.board.domain.MarketBoard;
import com.codestates.main07.marketBoard.board.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class MarketBoardService {
    private final MarketBoardRepository marketBoardRepository;

    public MarketBoardService(MarketBoardRepository marketBoardRepository) {
        this.marketBoardRepository = marketBoardRepository;
    }

    public MarketBoard createBoard(MarketBoard marketBoard) {
        marketBoard.setTag(Tag.SALE);
        return marketBoardRepository.save(marketBoard);
    }

    public MarketBoard updateBoard(MarketBoard marketBoard) {
        MarketBoard findMarketBoard = findCorrectMarketBoard(marketBoard.getMarketBoardId());
        findMarketBoard.setTag(Tag.SALE);

        findMarketBoard.update(marketBoard.getTitle(),
                marketBoard.getContent(),
                marketBoard.getPriceContent(),
                marketBoard.getPhoto());

        findMarketBoard.setModifiedAt(LocalDateTime.now());

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

    public Page<MarketBoard> boardList(Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),sort);

        return marketBoardRepository.findAll(pageable);
    }

    public Page<MarketBoard> myBoardList(int page, int size, long memberId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("marketBoardId").descending());
        return marketBoardRepository.findByMember_MemberId(pageable, memberId);
    }

    private MarketBoard findCorrectMarketBoard(long marketBoardId) {
        Optional<MarketBoard> optionalMarketBoard = marketBoardRepository.findById(marketBoardId);
        return optionalMarketBoard.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public void plusViewCount(MarketBoard marketBoard) {
        marketBoard.setViewCount(marketBoard.getViewCount() + 1);
    }
}
