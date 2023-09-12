package com.codestates.main07.clubBoard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ClubBoardResponseDto {
    private boolean success;
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
