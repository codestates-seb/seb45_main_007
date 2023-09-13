package com.codestates.main07.marketBoard.board.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MarketBoardUpdate {
    private long marketBoardId;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime modifiedAt;

    @Builder
    public MarketBoardUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}