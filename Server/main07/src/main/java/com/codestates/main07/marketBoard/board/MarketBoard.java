package com.codestates.main07.marketBoard.board;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MarketBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketBoardId;

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    @Column (nullable = false)
    private String content;

    @Lob
    private byte[] photo;

    @Column(nullable = false)
    private int viewCount;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

//    @Builder
//    public MarketBoard(String title, String content, byte[] photo, int viewCount) { //Member member 추가예정
//        this.title = title;
//        this.content = content;
//        this.photo = photo;
//        this.viewCount = viewCount;
//        this.member = member;
//    }

//    public void update(String title, String content, byte[] photo) {
//        this.title = title;
//        this.content = content;
//        this.photo = photo;
//    }
}
