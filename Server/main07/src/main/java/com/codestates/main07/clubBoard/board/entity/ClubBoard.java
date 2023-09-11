package com.codestates.main07.clubBoard.board.entity;

import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private int viewCount = 0;

    @OneToMany(mappedBy = "clubBoard") // mappedBy 에는 변수명 그대로 사용
    private List<ClubBoardComment> comments = new ArrayList<>();
}
