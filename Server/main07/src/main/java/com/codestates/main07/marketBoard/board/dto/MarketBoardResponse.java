package com.codestates.main07.marketBoard.board.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MarketBoardResponse {
    private long memberId;
    private long marketBoardId;
    private String title;
    private String content;
    private int viewCount;
    private String nickname;
    private boolean success;
    private LocalDateTime createdAt;
}
