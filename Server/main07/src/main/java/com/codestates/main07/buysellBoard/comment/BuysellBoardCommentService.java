package com.codestates.main07.buysellBoard.comment;

import com.codestates.main07.buysellBoard.board.dto.BuysellBoardResponseDto;

import java.util.List;

public interface BuysellBoardCommentService {

    public List<BuysellBoardComment> buysellBoardCommentList();

    public BuysellBoardResponseDto viewBuysellComment (Long id);
}
