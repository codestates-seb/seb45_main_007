package com.codestates.main07.clubBoard.comment.mapper;

import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentCreateDto;
import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentResponseDto;
import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentResponsesDto;
import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentUpdateDto;
import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubBoardCommentMapper {
    ClubBoardComment createDtoToComment(ClubBoardCommentCreateDto createDto);
    ClubBoardComment updateDtoToComment(ClubBoardCommentUpdateDto updateDto);
    ClubBoardCommentResponseDto commentToResponseDTo(ClubBoardComment comment);
    List<ClubBoardCommentResponsesDto> commentsToResponsesDto(List<ClubBoardComment> comments);
}
