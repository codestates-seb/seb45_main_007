package com.codestates.main07.memoryBoard.answer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemoryBoardAnswerResponseDto {
    private boolean success;
    private long memoryBoardAnswerId;
    private String content;
    private boolean adopted;

    // member 구현 후엔 memberId 대신 nickname 사용
    private long memberId;
//        private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 매핑 필요
    private long memoryBoardId;
}
