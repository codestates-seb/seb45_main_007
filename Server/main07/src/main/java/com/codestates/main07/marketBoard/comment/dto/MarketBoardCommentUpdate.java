package com.codestates.main07.marketBoard.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MarketBoardCommentUpdate {
    private String content;
    @Builder
    public MarketBoardCommentUpdate(String content) {
        this.content = content;
    }

}
