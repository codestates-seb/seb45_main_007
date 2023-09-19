package com.codestates.main07.clubBoard.board.mapper;

import com.codestates.main07.clubBoard.board.dto.ClubBoardCreateDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardResponseDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardResponsesDto;
import com.codestates.main07.clubBoard.board.dto.ClubBoardUpdateDto;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubBoardMapper {
    @Mapping(source = "memberId", target = "member.memberId")
    ClubBoard createDtoToClubBoard(ClubBoardCreateDto createDto);
    ClubBoard updateDtoToClubBoard(ClubBoardUpdateDto updateDto);

    @Mapping(source = "member.nickname", target = "nickname")
    ClubBoardResponseDto clubBoardToResponseDto(ClubBoard clubBoard);

    default List<ClubBoardResponsesDto> clubBoardsToResponsesDto(List<ClubBoard> clubBoards) {
        List<ClubBoardResponsesDto> responsesDto = new ArrayList<>();
        for (ClubBoard clubBoard : clubBoards) {
            responsesDto.add(new ClubBoardResponsesDto(clubBoard.getClubBoardId(),
                    clubBoard.getTitle(),
                    clubBoard.getContent(),
                    clubBoard.getPhoto(),
                    clubBoard.getVoice(),
                    clubBoard.getCategory(),
                    clubBoard.getMember().getNickname(),
                    clubBoard.getViewCount(),
                    clubBoard.getCreatedAt(),
                    clubBoard.getModifiedAt()));
        }
        return responsesDto;
    }
}
