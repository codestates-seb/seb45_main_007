package com.codestates.main07.clubBoard.board.mapper;

import com.codestates.main07.clubBoard.board.dto.ClubBoardCreateDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardResponseDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardResponsesDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardUpdateDto;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubBoardMapper {
    ClubBoard createDtoToClubBoard(ClubBoardCreateDto createDto);
    ClubBoard updateDtoToClubBoard(ClubBoardUpdateDto updateDto);

    @Mapping(source = "member.nickname", target = "nickname")
    ClubBoardResponseDto clubBoardToResponseDto(ClubBoard clubBoard);

    @Mapping(source = "member.nickname", target = "nickname")
    List<ClubBoardResponsesDto> clubBoardsToResponsesDto(List<ClubBoard> clubBoards);
}
