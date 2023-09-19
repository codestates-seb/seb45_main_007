package com.codestates.main07.clubBoardRecommend.service;

import com.codestates.main07.clubBoard.board.repository.ClubBoardRepository;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import com.codestates.main07.member.repository.MemberRepository;
import com.codestates.main07.clubBoardRecommend.dto.RecommendResponseDto;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;
import com.codestates.main07.clubBoardRecommend.repository.RecommendRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecommendService {
    private final RecommendRepository recommendRepository;
    private final ClubBoardRepository clubBoardRepository;
    private final MemberRepository memberRepository;

    public RecommendService(RecommendRepository recommendRepository, ClubBoardRepository clubBoardRepository,MemberRepository memberRepository) {
        this.recommendRepository = recommendRepository;
        this.clubBoardRepository = clubBoardRepository;
        this.memberRepository = memberRepository;
    }

    public boolean isRecommendedByMember(long clubBoardId, long memberId) {
        List<Recommend> recommendList = recommendRepository.findByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);
        return !recommendList.isEmpty();
    }

    public long getRecommendCount(long clubBoardId) {
        return recommendRepository.countByClubBoard_ClubBoardId(clubBoardId);
    }

    public ResponseEntity<RecommendResponseDto> createRecommend(long clubBoardId, long memberId, boolean recommended) {
        boolean isAlreadyRecommended = recommendRepository.existsByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);
        if (isAlreadyRecommended) {
            throw new IllegalStateException("해당 멤버는 이미 추천했습니다.");
        }

        Recommend recommend = new Recommend();
        ClubBoard clubBoard = clubBoardRepository.getById(clubBoardId);
        recommend.setClubBoard(clubBoard);
        recommend.setMember(memberRepository.getById(memberId));
        recommend.setRecommended(true); //좋아요를 누를 때 recommended를 true로 설정
        recommendRepository.save(recommend);

        updateRecommendCount(clubBoardId); //게시글의 추천 수 갱신

        long recommendCount = recommendRepository.countByClubBoard_ClubBoardId(clubBoardId);
        boolean updateIsRecommended = recommendRepository.existsByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);
        RecommendResponseDto responseDto = new RecommendResponseDto(recommendCount, updateIsRecommended);

        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<RecommendResponseDto> deleteRecommend(long clubBoardId, long memberId) {
        List<Recommend> recommendList = recommendRepository.findByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);
        if (!recommendList.isEmpty()) {
            Recommend recommend = recommendList.get(0);
            recommendRepository.delete(recommend);

            updateRecommendCount(clubBoardId); //좋아요 개수 갱신

            long recommendCount = recommendRepository.countByClubBoard_ClubBoardId(clubBoardId);
            boolean recommended = recommendRepository.existsByClubBoard_ClubBoardIdAndMember_MemberId(clubBoardId, memberId);
            RecommendResponseDto responseDto = new RecommendResponseDto(recommendCount, recommended);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void updateRecommendCount(long clubBoardId) {
        long recommendCount = recommendRepository.countByClubBoard_ClubBoardId(clubBoardId);
        ClubBoard clubBoard = clubBoardRepository.getById(clubBoardId);
        clubBoardRepository.save(clubBoard); //업데이트 된 추천 수를 저장
    }
}