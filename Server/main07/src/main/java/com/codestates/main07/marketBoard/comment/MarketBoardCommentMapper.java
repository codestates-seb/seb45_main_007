package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentResponse;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentCreate;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketBoardCommentMapper {

    MarketBoardComment createDtoToMarketBoardComment(MarketBoardCommentCreate createDto);

    MarketBoardComment updateDtoToMarketBoardComment(MarketBoardCommentUpdate updateDto);

    MarketBoardCommentResponse marketBoardCommentToMarketBoardCommentResponseDto(MarketBoardComment marketBoardComment);
}
