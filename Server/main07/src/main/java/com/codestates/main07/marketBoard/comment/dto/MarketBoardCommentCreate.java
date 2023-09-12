package com.codestates.main07.marketBoard.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MarketBoardCommentCreate {
    private long memberId;
    private long marketBoardCommentId;
    private String content;
}
