package com.codestates.main07.marketBoardLike.controller;

import com.codestates.main07.marketBoard.board.dto.MarketBoardResponse;
import com.codestates.main07.marketBoardLike.dto.LikePostDto;
import com.codestates.main07.marketBoardLike.dto.LikeResponseDto;
import com.codestates.main07.marketBoardLike.entity.Likes;
import com.codestates.main07.marketBoardLike.repository.LikeRepository;
import com.codestates.main07.marketBoardLike.service.LikeService;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Likes")
public class LikeController {
    private final LikeService likeServiceikeService;
    private final LikeRepository likeRepository;

    public LikeController(LikeService likeService, LikeRepository likeRepository) {
        this.likeService = likeService;
        this.likeRepository = likeRepository;
    }

    @GetMapping("/{marketBoard-id}/{member-id}")
    public ResponseEntity<Boolean> isLikedByMember(@PathVariable("marketBoard-id") long marketBoardId,
                                                         @PathVariable("member-id") long memberId) {
        boolean liked = likeService.isLikedByMember(marketBoardId, memberId);
        return ResponseEntity.ok(liked);
    }

    @PostMapping("/{marketBoard-id}/{member-id}")
    public ResponseEntity<LikeResponseDto> createLike(@PathVariable("marketBoard-id") long marketBoardId,
                                                                @PathVariable("member-id") long memberId,
                                                                @RequestBody LikePostDto LikePostDto) {
        boolean liked = LikePostDto.isLiked();
        likeService.createLike(marketBoardId, memberId, liked);

        LikeResponseDto responseDto = new LikeResponseDto(liked);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{marketBoard-id}/{member-id}")
    public ResponseEntity<LikeResponseDto> removeLike(@PathVariable("marketBoard-id") long marketBoardId,
                                                                @PathVariable("member-id") long memberId) {
        likeService.deleteLike(marketBoardId, memberId);

        boolean likeed = LikeRepository.existsByMarketBoard_MarketBoardIdAndMember_MemberId(marketBoardId, memberId);

        LikeResponseDto responseDto = new LikeResponseDto(liked);
        return ResponseEntity.ok(responseDto);
    }

}