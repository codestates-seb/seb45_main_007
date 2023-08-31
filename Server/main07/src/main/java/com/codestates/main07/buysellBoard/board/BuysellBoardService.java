package com.codestates.main07.buysellBoard.board;

import com.codestates.main07.buysellBoard.board.dto.BuysellBoardResponseDto;

import java.util.List;

public interface BuysellBoardService {

    public List<BuysellBoard> buysellBoardList();

    public BuysellBoardResponseDto viewBuysell (Long id);
}
