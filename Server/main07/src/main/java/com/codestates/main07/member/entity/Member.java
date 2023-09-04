package com.codestates.main07.member.entity;

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


@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성
@AllArgsConstructor // 멤버 클래스의 모든 멤버 변수를 파라미터로 갖는 멤버 생성자를 자동 생성
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(unique = true, length = 45)
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @Column(length = 16)
    @Size(min = 4, max = 16, message = "4자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z]*$", message = "한글 및 영어만 가능합니다.")
    private String username;

    @Column(length = 16)
    @Size(min = 4, max = 16, message = "4자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z]*$", message = "한글 및 영어만 가능합니다.")
    private String nickname;

    @Column(length = 16)
    @Size(min = 8, max = 16, message = "8자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W).*$", message = "영문, 숫자, 특수문자를 포함해주세요.")
    private String password;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

//    @OneToMany(mappedBy = "member")
//    private List<MemoryBoard> memoryBoards;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemoryBoardAnswer> memoryBoardAnswers;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemoryBoardAnswerComment> memoryBoardAnswerComments;
//
//    @OneToMany(mappedBy = "author")
//    private List<MemoryBoardAnswerAdopt> memoryBoardAnswerAdopts;
//
//    @OneToMany(mappedBy = "author")
//    private List<BuySellBoard> buySellBoards;
//
//    @OneToMany(mappedBy = "author")
//    private List<BuySellBoardComment> buySellBoardComments;
//
//    @OneToMany(mappedBy = "author")
//    private List<BuySellBoardVote> buySellBoardVotes;
}
