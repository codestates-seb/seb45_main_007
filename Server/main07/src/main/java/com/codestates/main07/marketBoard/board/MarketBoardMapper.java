package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.board.domain.MarketBoard;
import com.codestates.main07.marketBoard.board.dto.MarketBoardCreate;
import com.codestates.main07.marketBoard.board.dto.MarketBoardResponse;
import com.codestates.main07.marketBoard.board.dto.MarketBoardUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarketBoardMapper {

    MarketBoard createDtoToMarketBoard(MarketBoardCreate createDto);

    MarketBoard updateDtoToMarketBoard(MarketBoardUpdate updateDto);

    @Mappings({
            @Mapping(source = "member.nickname", target = "nickname"),
            @Mapping(source = "member.memberId", target = "memberId")
    })
    MarketBoardResponse marketBoardToMarketBoardResponseDto(MarketBoard marketBoard);
}
