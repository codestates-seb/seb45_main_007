package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.dto.MarketBoardResponseDto;

import java.util.List;

public interface MarketBoardService {

    public List<MarketBoard> marketBoardList();

    public MarketBoardResponseDto viewMarket (Long id);
}
