package com.codestates.main07.marketBoard.board.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketBoardResponse {
    private long memberId;
    private long marketBoardId;
    private String title;
    private String content;
//    private String photo;
    private int viewCount;
    private boolean success;
//    private List<>


//    public MarketBoardResponseDto(MarketBoard marketBoard) {
//        this.marketBoardId = marketBoard.getMarketBoardId();
//        this.title = marketBoard.getTitle();
//        this.content = marketBoard.getContent();
//        this.photo = marketBoard.getPhoto();
//        this.viewCount = marketBoard.getViewCount();
//    }
}
