package com.codestates.main07.marketBoard.board.dto;


import com.codestates.main07.marketBoard.board.MarketBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MarketBoardSaveDto {
    private long marketBoardId;
    private long memberId;
    private String title;
    private String content;
    private String photo;
    private int viewCount;

}
