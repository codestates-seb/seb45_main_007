package com.codestates.main07.marketBoard.board.dto;

import com.codestates.main07.clubBoard.response.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MarketBoardMultiResponseDto<T> {
    private boolean success;
    private List<T> marketBoards;
    private PageInfo pageInfo;

    public MarketBoardMultiResponseDto(List<T> marketBoards, Page page, boolean success) {
        this.marketBoards = marketBoards;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.success = success;
    }
}
