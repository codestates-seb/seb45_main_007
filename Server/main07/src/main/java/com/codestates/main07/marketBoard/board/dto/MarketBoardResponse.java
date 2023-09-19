package com.codestates.main07.marketBoard.board.dto;


import com.codestates.main07.marketBoard.board.domain.Tag;
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
    private int priceContent;
    private String photo;
    private int viewCount;
    private String nickname;
    private Tag tag;
    private boolean success;
    private LocalDateTime createdAt;
}
