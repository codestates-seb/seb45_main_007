package com.codestates.main07.clubBoard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoryBoardCreateDto {
    private long memberId;
    private String title;
    private String content;
    private String category;
}
