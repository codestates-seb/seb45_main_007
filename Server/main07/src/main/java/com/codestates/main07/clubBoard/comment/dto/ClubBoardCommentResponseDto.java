package com.codestates.main07.clubBoard.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClubBoardCommentResponseDto {
    private boolean success;
    private long clubBoardCommentId;
    private String content;
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime modifiedAt = LocalDateTime.now();

    // 댓글 작성자
    private String nickname;

    private long clubBoardId;
}
