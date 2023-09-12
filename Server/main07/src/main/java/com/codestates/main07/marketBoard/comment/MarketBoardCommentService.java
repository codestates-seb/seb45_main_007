package com.codestates.main07.marketBoard.comment;

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
public class MarketBoardCommentService {
    private final MarketBoardCommentRepository marketBoardCommentRepository;

    @Autowired
    public MarketBoardCommentService(MarketBoardCommentRepository marketBoardCommentRepository) {
        this.marketBoardCommentRepository = marketBoardCommentRepository;
    }

    public MarketBoardComment createComment(MarketBoardComment marketBoardComment) {
        return marketBoardCommentRepository.save(marketBoardComment);
    }

    public MarketBoardComment updateComment(MarketBoardComment marketBoardComment) {
        MarketBoardComment findMarketBoardComment = findCorrectMarketBoardComment(marketBoardComment.getMarketBoardCommentId());

        findMarketBoardComment.update(marketBoardComment.getContent());

        return marketBoardCommentRepository.save(findMarketBoardComment);
    }

    public void deleteComment (long marketBoardCommentId) {
        MarketBoardComment findMarketBoardComment = findCorrectMarketBoardComment(marketBoardCommentId);
        marketBoardCommentRepository.delete(findMarketBoardComment);
    }

    @Transactional(readOnly = true)
    public MarketBoardComment viewComment (long marketBoardCommentId) {
        return findCorrectMarketBoardComment(marketBoardCommentId);
    }

    public Page<MarketBoardComment> commentList (int page, int size) {
        return marketBoardCommentRepository.findAll(PageRequest.of
                (page, size, Sort.by("createAt").descending()));
    }

    private MarketBoardComment findCorrectMarketBoardComment(long marketBoardCommentId) {
        Optional<MarketBoardComment> optionalMarketBoardComment = marketBoardCommentRepository.findById(marketBoardCommentId);
        return optionalMarketBoardComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}
