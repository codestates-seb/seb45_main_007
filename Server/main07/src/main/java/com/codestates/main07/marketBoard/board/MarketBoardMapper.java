package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.dto.MarketBoardCreate;
import com.codestates.main07.marketBoard.board.dto.MarketBoardResponse;
import com.codestates.main07.marketBoard.board.dto.MarketBoardUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketBoardMapper {

    MarketBoard createDtoToMarketBoard(MarketBoardCreate createDto);

    MarketBoard updateDtoToMarketBoard(MarketBoardUpdate updateDto);

    MarketBoardResponse marketBoardToMarketBoardResponseDto(MarketBoard marketBoard);
}
