package com.codestates.main07.marketBoard.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MarketBoardCommentResponse {
    private long memberId;
    private long marketBoardId;
    private long marketBoardCommentId;
    private Long parentId;
    private String content;
    private String nickname;
    private boolean success;
    private LocalDateTime createdAt;
}
