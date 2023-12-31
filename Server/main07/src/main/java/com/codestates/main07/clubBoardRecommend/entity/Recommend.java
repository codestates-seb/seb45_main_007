package com.codestates.main07.clubBoardRecommend.entity;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clubBoardRecommendId;

    @Column
    private boolean recommended;
    private long recommendCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_board_id")
    private ClubBoard clubBoard;
}
