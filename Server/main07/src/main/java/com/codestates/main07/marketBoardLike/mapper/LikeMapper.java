package com.codestates.main07.marketBoardLike.mapper;

import com.codestates.main07.marketBoardLike.dto.LikePatchDto;
import com.codestates.main07.marketBoardLike.dto.LikePostDto;
import com.codestates.main07.marketBoardLike.dto.LikeResponseDto;
import com.codestates.main07.marketBoardLike.entity.Likes;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    Likes likePostDtoToLikes(LikePostDto likePostDto);
    Likes likePatchDtoToLikes(LikePatchDto likePatchDto);
    Likes likeResponseDtoToLikes(LikeResponseDto likeResponseDto);
    LikeResponseDto LikesToLikeResponseDto(Likes likes);
}