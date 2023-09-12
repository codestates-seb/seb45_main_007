package com.codestates.main07.clubBoardRecommend.mapper;

import com.codestates.main07.clubBoardRecommend.dto.RecommendPatchDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendPostDto;
import com.codestates.main07.clubBoardRecommend.dto.RecommendResponseDto;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecommendMapper {
    @Mapping(source = "member.nickname", target = "nickname")
    Recommend recommendPostDtoToRecommend(RecommendPostDto recommendPostDto);
    @Mapping(source = "member.nickname", target = "nickname")
    Recommend recommendPatchDtoToRecommend(RecommendPatchDto recommendPatchDto);
    @Mapping(source = "member.nickname", target = "nickname")
    Recommend recommendResponseDtoToRecommend(RecommendResponseDto recommendResponseDto);
    RecommendResponseDto recommendToRecommendResponseDto(Recommend recommend);
}