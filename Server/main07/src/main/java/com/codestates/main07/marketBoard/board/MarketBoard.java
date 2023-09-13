package com.codestates.main07.marketBoard.board;

import com.codestates.main07.marketBoard.comment.MarketBoardComment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MarketBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketBoardId;

    @Column (length = 100, nullable = false)
    private String title;

    @Column (nullable = false)
    private String content;

    @Column (columnDefinition = "integer default 0", nullable = false)
    private int viewCount;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @OneToMany (mappedBy = "marketBoard", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("marketBoardCommentId desc")
    private List<MarketBoardComment> comments;

    @Builder
    public MarketBoard(String title, String content, int viewCount) { //Member member 추가예정
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
//        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateView(int viewCount) {
        this.viewCount = viewCount;
    }
}
