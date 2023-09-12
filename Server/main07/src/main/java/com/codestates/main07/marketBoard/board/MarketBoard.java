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

    @Column (nullable = false)
    private int viewCount = 0;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @OneToMany (mappedBy = "marketBoard", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("marketBoardCommentId desc")
    private List<MarketBoardComment> comments;

    @Builder
    public MarketBoard(String title, String content, String photo) { //Member member 추가예정  //int viewCount
        this.title = title;
        this.content = content;
//        this.photo = photo;
        this.viewCount = viewCount;
//        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
