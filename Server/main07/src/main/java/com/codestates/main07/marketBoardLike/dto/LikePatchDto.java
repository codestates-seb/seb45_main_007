package com.codestates.main07.marketBoardLike.dto;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.marketBoard.board.MarketBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LikePatchDto {
    private long marketBoardLikeId;
    private boolean liked;
    private Member member;
    private MarketBoard marketBoard;
}