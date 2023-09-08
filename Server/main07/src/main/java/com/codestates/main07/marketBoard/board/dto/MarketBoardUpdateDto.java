package com.codestates.main07.marketBoard.board.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketBoardUpdateDto {
    private long marketBoardId;
    private String title;
    private String content;
    private byte[] photo;
    private int viewCount;

    @Builder
    public MarketBoardUpdateDto(String title, String content, byte[] photo, int viewCount) {
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.viewCount = viewCount;
    }
}
