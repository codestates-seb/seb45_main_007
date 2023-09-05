package com.codestates.main07.clubBoard.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ClubBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clubBoardId;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String category;

    @Column
    private boolean adopted;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column
    private LocalDateTime deletedAt;

    // 찾아줘 글 작성자
    private long memberId;
}
