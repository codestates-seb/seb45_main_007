package com.codestates.main07.clubBoardRecommend.mapper;

import com.codestates.main07.clubBoardRecommend.dto.RecommendPatchDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendPostDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendResponseDto;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecommendMapper {
    @Mapping(source = "clubBoard.clubBoardId", target = "clubBoardId")
    Recommend recommendPostDtoToRecommend(RecommendPostDto recommendPostDto);
    @Mapping(source = "clubBoard.clubBoardId", target = "clubBoardId")
    Recommend recommendPatchDtoToRecommend(RecommendPatchDto recommendPatchDto);
    @Mapping(source = "clubBoard.clubBoardId", target = "clubBoardId")
    Recommend recommendResponseDtoToRecommend(RecommendResponseDto recommendResponseDto);
    RecommendResponseDto recommendToRecommendResponseDto(Recommend recommend);
}