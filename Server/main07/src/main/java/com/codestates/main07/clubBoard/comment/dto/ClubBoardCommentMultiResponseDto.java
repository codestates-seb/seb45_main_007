package com.codestates.main07.clubBoard.comment.dto;

import com.codestates.main07.clubBoard.response.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ClubBoardCommentMultiResponseDto<T> {
    private boolean success;
    private List<T> clubBoardComments;
    private PageInfo pageInfo;

    public ClubBoardCommentMultiResponseDto(List<T> clubBoardComments, Page page, boolean success) {
        this.clubBoardComments = clubBoardComments;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.success = success;
    }
}
