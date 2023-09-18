package com.codestates.main07.clubBoardRecommend.controller;

import com.codestates.main07.clubBoard.board.dto.ClubBoardResponseDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendPostDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendResponseDto;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;
import com.codestates.main07.clubBoardRecommend.repository.RecommendRepository;
import com.codestates.main07.clubBoardRecommend.service.RecommendService;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommends")
public class RecommendController {
    private final RecommendService recommendService;
    private final RecommendRepository recommendRepository;

    public RecommendController(RecommendService recommendService, RecommendRepository recommendRepository) {
        this.recommendService = recommendService;
        this.recommendRepository = recommendRepository;
    }

    @GetMapping("/{clubBoardId}")
<<<<<<< HEAD
    public ResponseEntity<Long> getRecommendCount(@PathVariable long clubBoardId) {
=======
    public ResponseEntity<Long> getRecommendCount(@PathVariable("clubBoardId") long clubBoardId) {
>>>>>>> fb702b4773034424abd3fdfafba2dcad5ad6e2b2
        long recommendCount = recommendService.getRecommendCount(clubBoardId);
        return ResponseEntity.ok(recommendCount);
    }

    @GetMapping("/{clubBoardId}/{memberId}")
    public ResponseEntity<Boolean> isRecommendedByMember(@PathVariable long clubBoardId,
                                                         @PathVariable long memberId) {
        boolean recommended = recommendService.isRecommendedByMember(clubBoardId, memberId);
        return ResponseEntity.ok(recommended);
    }

    @PostMapping("/{clubBoardId}/{memberId}")
    public ResponseEntity<RecommendResponseDto> createRecommend(@PathVariable long clubBoardId,
                                                                @PathVariable long memberId,
                                                                @RequestBody RecommendPostDto recommendPostDto) {
        boolean recommended = recommendPostDto.isRecommended();
        recommendService.createRecommend(clubBoardId, memberId, recommended);
        long recommendCount = recommendService.getRecommendCount(clubBoardId);

        RecommendResponseDto responseDto = new RecommendResponseDto(recommendCount, recommended);
        responseDto.setSuccess(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{clubBoardId}/{memberId}")
    public ResponseEntity<RecommendResponseDto> removeRecommend(@PathVariable long clubBoardId,
                                                                @PathVariable long memberId) {
        recommendService.deleteRecommend(clubBoardId, memberId);

        long recommendCount = recommendRepository.countByClubBoard_ClubBoardId(clubBoardId);
        boolean recommended = recommendRepository.existsByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);

        RecommendResponseDto responseDto = new RecommendResponseDto(recommendCount, recommended);
        responseDto.setSuccess(true);
        return ResponseEntity.ok(responseDto);
    }

}
