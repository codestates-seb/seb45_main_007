package com.codestates.main07.memoryBoard.comment.mapper;

import com.codestates.main07.memoryBoard.comment.dto.MemoryBoardCommentCreateDto;
import com.codestates.main07.memoryBoard.comment.dto.MemoryBoardCommentResponseDto;
import com.codestates.main07.memoryBoard.comment.dto.MemoryBoardCommentResponsesDto;
import com.codestates.main07.memoryBoard.comment.dto.MemoryBoardCommentUpdateDto;
import com.codestates.main07.memoryBoard.comment.entity.MemoryBoardComment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoryBoardCommentMapper {
    MemoryBoardComment createDtoToComment(MemoryBoardCommentCreateDto createDto);
    MemoryBoardComment updateDtoToComment(MemoryBoardCommentUpdateDto updateDto);
    MemoryBoardCommentResponseDto commentToResponseDTo(MemoryBoardComment comment);
    List<MemoryBoardCommentResponsesDto> commentsToResponsesDto(List<MemoryBoardComment> comments);
}
