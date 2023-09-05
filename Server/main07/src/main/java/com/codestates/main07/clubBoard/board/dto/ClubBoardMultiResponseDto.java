package com.codestates.main07.clubBoard.board.dto;

import com.codestates.main07.clubBoard.response.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ClubBoardMultiResponseDto<T> {
    private boolean success;
    private List<T> clubBoards;
    private PageInfo pageInfo;

    public ClubBoardMultiResponseDto(List<T> clubBoards, Page page, boolean success) {
        this.clubBoards = clubBoards;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.success = success;
    }
}
