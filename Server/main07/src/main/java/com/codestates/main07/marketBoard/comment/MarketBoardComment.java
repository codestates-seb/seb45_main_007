package com.codestates.main07.marketBoard.comment;

import com.codestates.main07.marketBoard.board.MarketBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MarketBoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketBoardCommentId;

    @Column (nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_board_id")
    private MarketBoard marketBoard;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;


    public MarketBoardComment(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}
