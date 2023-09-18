package com.codestates.main07.clubBoardRecommend.dto;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RecommendResponseDto {
    private long clubBoardRecommendId;
    private boolean recommended;
    private boolean success;
    private long recommendCount;
    private Member member;
    private ClubBoard clubBoard;

    public RecommendResponseDto(long recommendCount, boolean recommended) {
        this.recommendCount = recommendCount;
        this.recommended = recommended;
    }
}
