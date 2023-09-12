package com.codestates.main07.clubBoardRecommend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendPatchDto {
    private long clubBoardId;
    private boolean recommended;
    private long recommendCount;
}