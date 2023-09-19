package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import com.codestates.main07.marketBoard.board.domain.MarketBoard;
import com.codestates.main07.marketBoard.board.MarketBoardRepository;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentCreate;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentUpdate;
import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class MarketBoardCommentService {
    private final MarketBoardCommentRepository marketBoardCommentRepository;
    private final MarketBoardRepository marketBoardRepository;
    private final MemberRepository memberRepository;
    private final MarketBoardCommentMapper mapper;

    public MarketBoardCommentService(MarketBoardCommentRepository marketBoardCommentRepository, MarketBoardRepository marketBoardRepository, MemberRepository memberRepository, MarketBoardCommentMapper mapper) {
        this.marketBoardCommentRepository = marketBoardCommentRepository;
        this.marketBoardRepository = marketBoardRepository;
        this.memberRepository = memberRepository;
        this.mapper = mapper;
    }

    public MarketBoardComment createComment(MarketBoardComment marketBoardComment, MarketBoardCommentCreate createDto) {
        Member member = memberRepository.findById(createDto.getMemberId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        MarketBoard marketBoard = marketBoardRepository.findById(marketBoardComment.getMarketBoard().getMarketBoardId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));

//        MarketBoardComment marketBoardComments = findCorrectMarketBoardComment(marketBoardComment.getMarketBoardCommentId());

        if (createDto.getParentId() != null) {
            // 대댓글 생성
            MarketBoardComment parentComment = findCorrectMarketBoardComment(createDto.getParentId());
            marketBoardComment.updateParent(parentComment);
            parentComment.getChildren().add(marketBoardComment);
            marketBoardComment.updateMember(member);
        } else {
            // 댓글 생성
            marketBoardComment.updateBoard(marketBoard);
            marketBoardComment.updateMember(member);
        }
        marketBoardComment.update(marketBoardComment.getContent());
        return marketBoardCommentRepository.save(marketBoardComment);
    }

    public MarketBoardComment updateComment(long marketBoardCommentId,MarketBoardCommentUpdate updateDto) {

        MarketBoardComment findMarketBoardComment = findCorrectMarketBoardComment(marketBoardCommentId);

        findMarketBoardComment.update(updateDto.getContent());

        findMarketBoardComment.setModifiedAt(LocalDateTime.now());

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

    public MarketBoardComment findComment(long marketBoardCommentId) {
        return findCorrectMarketBoardComment(marketBoardCommentId);
    }

    private MarketBoardComment findCorrectMarketBoardComment(long marketBoardCommentId) {
        Optional<MarketBoardComment> optionalMarketBoardComment = marketBoardCommentRepository.findById(marketBoardCommentId);
        return optionalMarketBoardComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    public Page<MarketBoardComment> commentListByMarketBoardId(Pageable pageable, long marketBoardId) {
        return marketBoardCommentRepository.findByMarketBoard_MarketBoardId(pageable, marketBoardId);
    }

}
