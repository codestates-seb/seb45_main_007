package com.codestates.main07.memory.board.mapper;

import com.codestates.main07.memory.board.dto.MemoryBoardCreateDto;
import com.codestates.main07.memory.board.dto.MemoryBoardResponseDto;
import com.codestates.main07.memory.board.dto.MemoryBoardResponsesDto;
import com.codestates.main07.memory.board.dto.MemoryBoardUpdateDto;
import com.codestates.main07.memory.board.entity.MemoryBoard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoryBoardMapper {
    MemoryBoard createDtoToMemoryBoard(MemoryBoardCreateDto createDto);
    MemoryBoard updateDtoToMemoryBoard(MemoryBoardUpdateDto updateDto);
    MemoryBoardResponseDto memoryBoardToResponseDto(MemoryBoard memoryBoard);
    List<MemoryBoardResponsesDto> memoryBoardsToResponsesDto(List<MemoryBoard> memoryBoards);
}
