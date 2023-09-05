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
    private String category;

    // member 구현 후엔 memberId 대신 nickname 사용
    private long memberId;
//        private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
