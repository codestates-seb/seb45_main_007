package com.codestates.main07.clubBoardRecommend.dto;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RecommendPatchDto {
    private long clubBoardRecommendId;
    private boolean recommended;
    private long recommendCount;
    private Member member;
    private ClubBoard clubBoard;
}