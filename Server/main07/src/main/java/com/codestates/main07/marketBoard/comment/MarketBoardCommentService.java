package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import com.codestates.main07.marketBoard.board.MarketBoard;
import com.codestates.main07.marketBoard.board.MarketBoardRepository;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class MarketBoardCommentService {
    private final MarketBoardCommentRepository marketBoardCommentRepository;
    private final MarketBoardRepository marketBoardRepository;
//    private final MemberRepository memberRepository;
    private final MarketBoardCommentMapper mapper;

    public MarketBoardCommentService(MarketBoardCommentRepository marketBoardCommentRepository, MarketBoardRepository marketBoardRepository, MarketBoardCommentMapper mapper) {
        this.marketBoardCommentRepository = marketBoardCommentRepository;
        this.marketBoardRepository = marketBoardRepository;
        this.mapper = mapper;
    }

    public MarketBoardComment createComment(MarketBoardComment marketBoardComment) {
        return marketBoardCommentRepository.save(marketBoardComment);
    }

    public MarketBoardComment createCommentOrReply(MarketBoardComment marketBoardComment, MarketBoardCommentCreate createDto) {
        if (createDto.getParentId() != null) {
            // 대댓글 생성
            MarketBoardComment parentComment = findCorrectMarketBoardComment(createDto.getParentId());
            marketBoardComment.updateParent(parentComment);
            parentComment.getChildren().add(marketBoardComment);
        } else {
            // 댓글 생성
            MarketBoard marketBoard = marketBoardRepository.findById(createDto.getMarketBoardId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
            marketBoardComment.updateBoard(marketBoard);
        }
        marketBoardComment.update(createDto.getContent());
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

    public Page<MarketBoardComment> commentList (Pageable pageable) {
        return marketBoardCommentRepository.findAll(pageable);
    }

    private MarketBoardComment findCorrectMarketBoardComment(long marketBoardCommentId) {
        Optional<MarketBoardComment> optionalMarketBoardComment = marketBoardCommentRepository.findById(marketBoardCommentId);
        return optionalMarketBoardComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}
