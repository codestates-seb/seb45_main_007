package com.codestates.main07.marketBoardLike.service;

import com.codestates.main07.marketBoard.board.MarketBoardRepository;
import com.codestates.main07.marketBoard.board.MarketBoard;
import com.codestates.main07.member.repository.MemberRepository;
import com.codestates.main07.marketBoardLike.dto.LikeResponseDto;
import com.codestates.main07.marketBoardLike.entity.Likes;
import com.codestates.main07.marketBoardLike.repository.LikeRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final MarketBoardRepository marketBoardRepository;
    private final MemberRepository memberRepository;

    public LikeService(LikeRepository likeRepository, MarketBoardRepository marketBoardRepository,MemberRepository memberRepository) {
        this.likeRepository = likeRepository;
        this.marketBoardRepository = marketBoardRepository;
        this.memberRepository = memberRepository;
    }

    public boolean isLikedByMember(long marketBoardId, long memberId) {
        List<Like> likeList = likeRepository.findByMarketBoard_MarketBoardIdAndMember_MemberId(marketBoardId, memberId);
        return !likeList.isEmpty();
    }

    public ResponseEntity<LikeResponseDto> createLike(long marketBoardId, long memberId, boolean liked) {
        boolean isAlreadyLiked = likeRepository.existsByMarketBoard_MarketBoardIdAndMember_MemberId(marketBoardId,memberId);
        if (isAlreadyLiked) {
            throw new IllegalStateException("해당 멤버는 이미 추천했습니다.");
        }

        Like like = new Like();
        MarketBoard marketBoard = marketBoardRepository.getById(marketBoardId);
        like.setMarketBoard(marketBoard);
        like.setMember(memberRepository.getById(memberId));
        like.setLiked(true); //좋아요를 누를 때 liked를 true로 설정
        likeRepository.save(like);

        boolean updateIsLiked = likeRepository.existsByMarketBoard_MarketBoardIdAndMember_MemberId(marketBoardId, memberId);
        LikeResponseDto responseDto = new LikeResponseDto(updateIsLiked);

        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<LikeResponseDto> deleteLike(long marketBoardId, long memberId) {
        List<Like> likeList = likeRepository.findByMarketBoard_MarketBoardIdAndMember_MemberId(marketBoardId, memberId);
        if (!likeList.isEmpty()) {
            Like like = likeList.get(0);
            likeRepository.delete(like);

            boolean liked = likeRepository.existsByMarketBoard_MarketBoardIdAndMember_MemberId(marketBoardId, memberId);
            LikeResponseDto responseDto = new LikeResponseDto(liked);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}