package com.codestates.main07.marketBoard.board.dto;


import com.codestates.main07.marketBoard.board.MarketBoard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class MarketBoardResponseDto {
    private long memberId;
    private long marketBoardId;
    private String title;
    private String content;
    private String photo;
    private int viewCount;


//    public MarketBoardResponseDto(MarketBoard marketBoard) {
//        this.marketBoardId = marketBoard.getMarketBoardId();
//        this.title = marketBoard.getTitle();
//        this.content = marketBoard.getContent();
//        this.photo = marketBoard.getPhoto();
//        this.viewCount = marketBoard.getViewCount();
//    }
}
