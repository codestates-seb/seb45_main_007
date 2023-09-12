package com.codestates.main07.clubBoard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ClubBoardResponsesDto {
    private long clubBoardId;
    private String title;
    private String content;
    private byte[] photo;
    private byte[] voice;
    private String category;

    // 게시글 작성자
    private String nickname;

    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
