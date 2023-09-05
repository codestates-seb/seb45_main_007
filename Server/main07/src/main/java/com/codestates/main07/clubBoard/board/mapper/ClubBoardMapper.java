package com.codestates.main07.clubBoard.board.mapper;

import com.codestates.main07.clubBoard.board.dto.ClubBoardCreateDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardResponseDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardResponsesDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardUpdateDto;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubBoardMapper {
    ClubBoard createDtoToClubBoard(ClubBoardCreateDto createDto);
    ClubBoard updateDtoToClubBoard(ClubBoardUpdateDto updateDto);
    ClubBoardResponseDto clubBoardToResponseDto(ClubBoard clubBoard);
    List<ClubBoardResponsesDto> clubBoardsToResponsesDto(List<ClubBoard> clubBoards);
}
