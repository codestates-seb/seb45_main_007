package com.codestates.main07.marketBoard.board.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MarketBoardCreate {
    private long marketBoardId;
    private long memberId;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
}
