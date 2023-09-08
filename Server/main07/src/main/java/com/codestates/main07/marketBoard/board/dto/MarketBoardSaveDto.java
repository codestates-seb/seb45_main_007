package com.codestates.main07.marketBoard.board.dto;


import com.codestates.main07.marketBoard.board.MarketBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MarketBoardSaveDto {
    private BoardSaveInfo boardSaveInfo;
    private long marketBoardId;

    @Getter
    @Setter
    public static class BoardSaveInfo {
//        private long memberId;
        private String title;
        private String content;
//        private byte[] photo;
        private int viewCount;
    }
}
