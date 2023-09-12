package com.codestates.main07.marketBoard.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketBoardCommentResponse {
    private long memberId;
    private long marketBoardCommentId;
    private String content;
    private boolean success;
}
