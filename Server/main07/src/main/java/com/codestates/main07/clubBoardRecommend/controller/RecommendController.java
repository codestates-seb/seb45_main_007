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
@RequestMapping("/clubBoards")
public class RecommendController {
    private final RecommendService recommendService;
    private final RecommendRepository recommendRepository;

    public RecommendController(RecommendService recommendService, RecommendRepository recommendRepository) {
        this.recommendService = recommendService;
        this.recommendRepository = recommendRepository;
    }

    @GetMapping("/{clubBoard-id}/recommends")
    public ResponseEntity<Long> getRecommendCount(@PathVariable("clubBoard-id") long clubBoardId) {
        long recommendCount = recommendService.getRecommendCount(clubBoardId);
        return ResponseEntity.ok(recommendCount);
    }

    @GetMapping("/{clubBoard-id}/{member-id}/recommended")
    public ResponseEntity<Boolean> isRecommendedByMember(@PathVariable("clubBoard-id") long clubBoardId,
                                                         @PathVariable("member-id") long memberId) {
        boolean recommended = recommendService.isRecommendedByMember(clubBoardId, memberId);
        return ResponseEntity.ok(recommended);
    }

    @PostMapping("/{clubBoard-id}/recommends")
    public ResponseEntity<RecommendResponseDto> createRecommend(@PathVariable("clubBoard-id") long clubBoardId,
                                                                @PathVariable("member-id") long memberId,
                                                                @RequestBody RecommendPostDto recommendPostDto) {
        boolean recommended = recommendPostDto.isRecommended();
        recommendService.createRecommend(clubBoardId, memberId, recommended);
        long recommendCount = recommendService.getRecommendCount(clubBoardId);

        //boolean updatedRecommended = recommendRepository.existsByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);

        RecommendResponseDto responseDto = new RecommendResponseDto(recommendCount, recommended);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{clubBoard-id}/recommends")
    public ResponseEntity<RecommendResponseDto> removeRecommend(@PathVariable("clubBoard-id") long clubBoardId,
                                                                @PathVariable("member-id") long memberId) {
        recommendService.deleteRecommend(clubBoardId, memberId);

        long recommendCount = recommendRepository.countByClubBoard_ClubBoardId(clubBoardId);
        boolean recommended = recommendRepository.existsByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);

        //boolean updatedRecommended = recommendRepository.existsByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);

        RecommendResponseDto responseDto = new RecommendResponseDto(recommendCount, recommended);
        return ResponseEntity.ok(responseDto);
    }

}
