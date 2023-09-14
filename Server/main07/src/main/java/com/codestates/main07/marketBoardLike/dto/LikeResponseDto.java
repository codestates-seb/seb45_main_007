package com.codestates.main07.maketBoardLike.dto;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.maketBoard.board.MaketBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LikeResponseDto {
    private long maketBoardLikeId;
    private boolean liked;
    private Member member;
    private MarketBoard marketBoard;

    public LikeResponseDto(boolean liked) {
        this.liked = recommended;
    }
}