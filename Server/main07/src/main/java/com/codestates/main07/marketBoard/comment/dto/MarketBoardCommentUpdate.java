package com.codestates.main07.marketBoard.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketBoardCommentUpdate {
    private long memberId;
    private long marketBoardCommentId;
    private String content;
}
