package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.board.MarketBoard;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MarketBoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketBoardCommentId;

    @Column (columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_board_id")
    private MarketBoard marketBoard;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MarketBoardComment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<MarketBoardComment> children = new ArrayList<>();

//    public void updateMember(Memeber memeber) {
//        this.member = memeber;
//    }

    public void updateBoard(MarketBoard marketBoard) {
        this.marketBoard = marketBoard;
    }

    public void updateParent(MarketBoardComment marketBoardComment) {
        this.parent = marketBoardComment;
    }

    @Builder
    public MarketBoardComment(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

    public void createReply(String content) {
        this.content = content;
    }
}
