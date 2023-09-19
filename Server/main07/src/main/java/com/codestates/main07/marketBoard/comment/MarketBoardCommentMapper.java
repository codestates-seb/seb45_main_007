package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentResponse;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentCreate;
import com.codestates.main07.marketBoard.comment.dto.MarketBoardCommentUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarketBoardCommentMapper {

    @Mapping(source = "marketBoardId", target = "marketBoard.marketBoardId")
    MarketBoardComment createDtoToMarketBoardComment(MarketBoardCommentCreate createDto);

    MarketBoardComment updateDtoToMarketBoardComment(MarketBoardCommentUpdate updateDto);

    @Mappings({
            @Mapping(source = "marketBoardComment.parent.marketBoardCommentId", target = "parentId"),
            @Mapping(source = "member.memberId", target = "memberId"),
            @Mapping(source = "member.nickname", target = "nickname"),
            @Mapping(source = "marketBoard.marketBoardId", target = "marketBoardId")
    })
    MarketBoardCommentResponse marketBoardCommentToMarketBoardCommentResponseDto(MarketBoardComment marketBoardComment);
}
