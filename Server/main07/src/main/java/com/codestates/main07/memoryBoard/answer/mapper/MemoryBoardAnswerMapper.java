package com.codestates.main07.memoryBoard.answer.mapper;

import com.codestates.main07.memoryBoard.answer.dto.MemoryBoardAnswerCreateDto;
import com.codestates.main07.memoryBoard.answer.dto.MemoryBoardAnswerResponseDto;
import com.codestates.main07.memoryBoard.answer.dto.MemoryBoardAnswerResponsesDto;
import com.codestates.main07.memoryBoard.answer.dto.MemoryBoardAnswerUpdateDto;
import com.codestates.main07.memoryBoard.answer.entity.MemoryBoardAnswer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoryBoardAnswerMapper {
    MemoryBoardAnswer createDtoToAnswer(MemoryBoardAnswerCreateDto createDto);
    MemoryBoardAnswer updateDtoToAnswer(MemoryBoardAnswerUpdateDto updateDto);
    MemoryBoardAnswerResponseDto answerToResponseDto(MemoryBoardAnswer answer);
    List<MemoryBoardAnswerResponsesDto> answersToResponsesDto(List<MemoryBoardAnswer> answers);
}
