package com.codestates.main07.clubBoard.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClubBoardCommentResponsesDto {
    private long clubBoardCommentId;
    private String content;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    // 댓글 작성자
    private String nickname;

    private long clubBoardId;

    public ClubBoardCommentResponsesDto(long clubBoardCommentId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String nickname, long clubBoardId) {
        this.clubBoardCommentId = clubBoardCommentId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.nickname = nickname;
        this.clubBoardId = clubBoardId;
    }
}
