package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.dto.BuysellBoardResponseDto;

import java.util.List;

public interface MarketBoardService {

    public List<MarketBoard> buysellBoardList();

    public BuysellBoardResponseDto viewBuysell (Long id);
}
