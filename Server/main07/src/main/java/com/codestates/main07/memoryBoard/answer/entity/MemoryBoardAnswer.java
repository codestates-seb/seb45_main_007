package com.codestates.main07.memoryBoard.answer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MemoryBoardAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memoryBoardAnswerId;

    @Column(columnDefinition = "TEXT")
    private String content;
    private boolean adopted;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();
    private LocalDateTime deletedAt;

    // 찾아줘 답변 작성자
    private long memberId;

    // 매핑 필요
    private long memoryBoardId;
}
