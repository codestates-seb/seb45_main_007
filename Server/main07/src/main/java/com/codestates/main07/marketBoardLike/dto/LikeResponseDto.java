package com.codestates.main07.marketBoardLike.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LikeResponseDto {
    private boolean liked;
    private boolean success;

    public LikeResponseDto(boolean liked) {
        this.liked = liked;
    }
}
