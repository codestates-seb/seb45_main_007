package com.codestates.main07.clubBoard.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemoryBoardCommentResponseDto {
    private boolean success;
    private long memoryBoardCommentId;
    private String content;
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime modifiedAt = LocalDateTime.now();

    // 댓글 작성자
    private long memberId;

    // 매핑 예정
    private long memoryBoardAnswerId;
}