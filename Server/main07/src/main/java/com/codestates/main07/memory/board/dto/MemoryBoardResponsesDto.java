package com.codestates.main07.memory.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemoryBoardResponsesDto {
    private long memoryBoardId;
    private String title;
    private String content;
    private String category;
    private boolean adopted;

    // member 구현 후엔 memberId 대신 nickname 사용
    private long memberId;
//        private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
