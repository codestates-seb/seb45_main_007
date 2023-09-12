package com.codestates.main07.marketBoard.board.dto;


import lombok.*;

@Getter
@AllArgsConstructor
public class MarketBoardCreate {
    private long marketBoardId;
    private long memberId;
    private String title;
    private String content;
//    private String photo;
    private int viewCount;

}
