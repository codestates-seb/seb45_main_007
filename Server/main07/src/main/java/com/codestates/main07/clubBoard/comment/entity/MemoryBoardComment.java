package com.codestates.main07.clubBoard.comment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MemoryBoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoryBoardCommentId;

    @Column(columnDefinition = "TEXT")
    private String content;

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime modifiedAt = LocalDateTime.now();
    LocalDateTime deletedAt;

    // 댓글 작성자
    private long memberId;
}
