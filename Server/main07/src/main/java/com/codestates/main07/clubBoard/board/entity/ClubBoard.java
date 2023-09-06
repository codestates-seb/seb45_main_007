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

    @Lob
    @Column
    private String content;

    @Lob
    @Column
    private byte[] photo;

    @Lob
    @Column
    private byte[] voice;

    @Column
    private String category;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column
    private LocalDateTime deletedAt;

    // 찾아줘 글 작성자
    private long memberId;
}
