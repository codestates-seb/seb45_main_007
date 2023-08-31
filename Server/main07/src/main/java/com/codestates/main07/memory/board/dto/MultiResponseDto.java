package com.codestates.main07.memory.board.dto;

import com.codestates.main07.memory.pagination.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponseDto<T> {
    private boolean success;
    private List<T> memoryBoards;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> memoryBoards, Page page, boolean success) {
        this.memoryBoards = memoryBoards;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.success = success;
    }
}
