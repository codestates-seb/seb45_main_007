package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.dto.MarketBoardResponseDto;
import com.codestates.main07.marketBoard.board.dto.MarketBoardSaveDto;
import com.codestates.main07.marketBoard.board.dto.MarketBoardUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketBoardMapper {

    MarketBoard saveMarketBoardSaveDtoToMarketBoard(MarketBoardSaveDto marketBoardSaveDto);

    MarketBoard updateMarketBoardUpdateDtoToMarketBoard(MarketBoardUpdateDto marketBoardUpdateDto);

    MarketBoardResponseDto marketBoardToMarketBoardResponseDto(MarketBoard marketBoard);
}
