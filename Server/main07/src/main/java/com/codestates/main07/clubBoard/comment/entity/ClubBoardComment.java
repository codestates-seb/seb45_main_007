package com.codestates.main07.clubBoard.comment.entity;

import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ClubBoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clubBoardCommentId;

    @Column(columnDefinition = "TEXT")
    private String content;

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime modifiedAt = LocalDateTime.now();
    LocalDateTime deletedAt;

    // 댓글 작성자
    private long memberId;

    @ManyToOne // many = comment, one = board
    @JoinColumn(columnDefinition = "club_board_id")
    private ClubBoard clubBoard;
}
