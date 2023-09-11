package com.codestates.main07.clubBoardRecommend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RecommendResponseDto {
    private boolean recommended;
    private long recommendCount;

    public RecommendResponseDto(long recommendCount, boolean recommended) {
        this.recommendCount = recommendCount;
        this.recommended = recommended;
    }
}