package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.board.dto.BuysellBoardResponseDto;

import java.util.List;

public interface MarketBoardCommentService {

    public List<MarketBoardComment> buysellBoardCommentList();

    public BuysellBoardResponseDto viewBuysellComment (Long id);
}
