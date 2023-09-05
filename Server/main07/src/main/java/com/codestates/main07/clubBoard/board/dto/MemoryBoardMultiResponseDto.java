package com.codestates.main07.clubBoard.board.dto;

import com.codestates.main07.clubBoard.response.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MemoryBoardMultiResponseDto<T> {
    private boolean success;
    private List<T> memoryBoards;
    private PageInfo pageInfo;

    public MemoryBoardMultiResponseDto(List<T> memoryBoards, Page page, boolean success) {
        this.memoryBoards = memoryBoards;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.success = success;
    }
}
