package com.codestates.main07.clubBoard.comment.entity;

import com.codestates.main07.audit.Audit;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import com.codestates.main07.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ClubBoardComment extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clubBoardCommentId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne // many = comment, one = member
    @JoinColumn(columnDefinition = "member_id")
    private Member member; // 댓글 작성자

    @ManyToOne // many = comment, one = board
    @JoinColumn(columnDefinition = "club_board_id")
    private ClubBoard clubBoard;
}
