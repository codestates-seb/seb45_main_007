package com.codestates.main07.clubBoardRecommend.mapper;

import com.codestates.main07.clubBoardRecommend.dto.RecommendPatchDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendPostDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendResponseDto;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecommendMapper {
    Recommend recommendPostDtoToRecommend(RecommendPostDto recommendPostDto);
    Recommend recommendPatchDtoToRecommend(RecommendPatchDto recommendPatchDto);
    Recommend recommendResponseDtoToRecommend(RecommendResponseDto recommendResponseDto);
    RecommendResponseDto RecommendToRecommendResponseDto(Recommend recommend);
}