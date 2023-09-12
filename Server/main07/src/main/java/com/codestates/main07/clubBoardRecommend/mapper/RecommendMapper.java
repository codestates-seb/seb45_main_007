package com.codestates.main07.clubBoardRecommend.mapper;

import com.codestates.main07.clubBoardRecommend.dto.RecommendPatchDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendPostDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendResponseDto;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecommendMapper {
    @Mapping(source = "recommended", target = "recommended2")
    Recommend recommendPostDtoToRecommend(RecommendPostDto recommendPostDto);
    @Mapping(source = "recommended", target = "recommended2")
    Recommend recommendPatchDtoToRecommend(RecommendPatchDto recommendPatchDto);
    @Mapping(source = "recommended", target = "recommended2")
    Recommend recommendResponseDtoToRecommend(RecommendResponseDto recommendResponseDto);
    RecommendResponseDto recommendToRecommendResponseDto(Recommend recommend);
}