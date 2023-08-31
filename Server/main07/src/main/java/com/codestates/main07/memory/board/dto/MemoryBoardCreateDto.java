package com.codestates.main07.memory.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoryBoardCreateDto {
    private String memberId;
    private String title;
    private String content;
    private String category;
}
