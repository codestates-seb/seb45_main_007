package com.codestates.main07.marketBoard.board.dto;


import com.codestates.main07.marketBoard.board.domain.Tag;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MarketBoardCreate {
    private long memberId;
    private String title;
    private String content;
    private int priceContent;
    private String photo;
    private Tag tag;
}
