package com.codestates.main07.clubBoard.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClubBoardCommentResponsesDto {
    private long clubBoardCommentId;
    private String content;
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime modifiedAt = LocalDateTime.now();

    // 댓글 작성자
    private long memberId;

    private long clubBoardId;

    public ClubBoardCommentResponsesDto(long clubBoardCommentId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, long memberId, long clubBoardId) {
        this.clubBoardCommentId = clubBoardCommentId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.memberId = memberId;
        this.clubBoardId = clubBoardId;
    }
}
