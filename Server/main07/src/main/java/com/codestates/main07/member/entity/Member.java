package com.codestates.main07.member.entity;

import com.codestates.main07.audit.Audit;
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

    @Column(length = 16)
    @Size(min = 8, max = 16, message = "8자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9\\W]).*$", message = "영문, 숫자, 특수문자 중 하나 이상을 포함해야 합니다.")
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

//    @OneToMany(mappedBy = "member")
//    private List<MemoryBoard> memoryBoards;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemoryBoardAnswer> memoryBoardAnswers;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemoryBoardAnswerComment> memoryBoardAnswerComments;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemoryBoardAnswerAdopt> memoryBoardAnswerAdopts;
//
//    @OneToMany(mappedBy = "member")
//    private List<BuySellBoard> buySellBoards;
//
//    @OneToMany(mappedBy = "member")
//    private List<BuySellBoardComment> buySellBoardComments;
//
//    @OneToMany(mappedBy = "member")
//    private List<BuySellBoardVote> buySellBoardVotes;
}