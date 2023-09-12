package com.codestates.main07.clubBoard.comment.mapper;

import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentCreateDto;
import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentResponseDto;
import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentResponsesDto;
import com.codestates.main07.clubBoard.comment.dto.ClubBoardCommentUpdateDto;
import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubBoardCommentMapper {
    ClubBoardComment createDtoToComment(ClubBoardCommentCreateDto createDto);
    ClubBoardComment updateDtoToComment(ClubBoardCommentUpdateDto updateDto);

    @Mapping(source = "clubBoard.clubBoardId", target = "clubBoardId")
    @Mapping(source = "member.nickname", target = "nickname")
    ClubBoardCommentResponseDto commentToResponseDTo(ClubBoardComment comment);

    default List<ClubBoardCommentResponsesDto> commentsToResponsesDto(List<ClubBoardComment> comments) {
        List<ClubBoardCommentResponsesDto> responsesDto = new ArrayList<>();
        for (ClubBoardComment comment : comments) {
            responsesDto.add(new ClubBoardCommentResponsesDto(comment.getClubBoardCommentId(),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt(),
                    comment.getMember().getNickname(),
                    comment.getClubBoard().getClubBoardId()));
        }
        return responsesDto;
    }
}
