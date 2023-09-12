package com.codestates.main07.clubBoard.board.entity;

import com.codestates.main07.audit.Audit;
import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import com.codestates.main07.clubBoardRecommend.entity.Recommend;
import com.codestates.main07.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ClubBoard extends Audit {
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

    private int viewCount = 0;

    @ManyToOne // many = board, one = member
    @JoinColumn(columnDefinition = "member_id")
    private Member member; // 게시글 작성자

    @OneToMany(mappedBy = "clubBoard") // mappedBy 에는 변수명 그대로 사용
    private List<ClubBoardComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "clubBoard") 
    private List<Recommend> recommends = new ArrayList<>();
}
