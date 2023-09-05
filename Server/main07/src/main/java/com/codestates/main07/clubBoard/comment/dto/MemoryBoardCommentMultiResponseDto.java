package com.codestates.main07.clubBoard.comment.dto;

import com.codestates.main07.clubBoard.response.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MemoryBoardCommentMultiResponseDto<T> {
    private boolean success;
    private List<T> memoryBoardComments;
    private PageInfo pageInfo;

    public MemoryBoardCommentMultiResponseDto(List<T> memoryBoardComments, Page page, boolean success) {
        this.memoryBoardComments = memoryBoardComments;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.success = success;
    }
}
