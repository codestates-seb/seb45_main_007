package com.codestates.main07.member.entity;

import com.codestates.main07.audit.Audit;
import com.codestates.main07.clubBoard.board.entity.ClubBoard;
import com.codestates.main07.clubBoard.comment.entity.ClubBoardComment;
import com.codestates.main07.marketBoard.board.MarketBoard;
import com.codestates.main07.marketBoard.comment.MarketBoardComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성
@AllArgsConstructor // 멤버 클래스의 모든 멤버 변수를 파라미터로 갖는 멤버 생성자를 자동 생성
@Entity
public class Member extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(unique = true, length = 45)
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @Column(length = 16)
    @Size(min = 2, max = 16, message = "2자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z]*$", message = "한글 및 영어만 가능합니다.")
    private String username;

    @Column(length = 16)
    @Size(min = 1, max = 16, message = "1자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z]*$", message = "한글 및 영어만 가능합니다.")
    private String nickname;

//    해싱된 비밀번호가 저장되기 때문에 유효성 검사에 실패하여 조건을 삭제하였음.
//    대신 PostDto에 비밀번호에 대한 조건을 추가하여 회원등록 시 올바른 비밀번호 형식을 유지하게끔 하였음.
    @Column(length = 64) // SHA-256 해싱 방법을 사용하기 위한 크기 설정
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "role")
    private Set<String> roles;

//    audit 브랜치에서 클래스 생성 예정
//    @CreatedDate
//    @Column(updatable = false)
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "member")
    private List<ClubBoard> clubBoards;

    @OneToMany(mappedBy = "member")
    private List<ClubBoardComment> clubBoardComments;

    @OneToMany(mappedBy = "member")
    private List<MarketBoard> marketBoards;

    @OneToMany(mappedBy = "member")
    private List<MarketBoardComment> marketBoardComments;

//    @OneToMany(mappedBy = "member")
//    private List<BuySellBoardVote> buySellBoardVotes;
}
