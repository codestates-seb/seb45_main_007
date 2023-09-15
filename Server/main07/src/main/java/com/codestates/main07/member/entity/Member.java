package com.codestates.main07.member.entity;

import com.codestates.main07.audit.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성
@AllArgsConstructor // 멤버 클래스의 모든 멤버 변수를 파라미터로 갖는 멤버 생성자를 자동 생성
@Entity
public class Member extends Audit {

    public enum Status {
        ACTIVE,            // 활성 상태의 회원
        INACTIVE,          // 비활성 상태의 회원 (일시적으로 사용 중지)
        PENDING_VERIFICATION, // 이메일 인증 대기 중
        BANNED,            // 제제 받은 회원
        DELETED,           // 삭제된 회원 (탈퇴 혹은 관리자에 의한 삭제)
        SUSPENDED          // 일시 정지된 회원 (예: 위반 행위로 인한 일시 정지)
    }

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

    @Column(length = 16, unique = true)
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

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status = Status.ACTIVE;  // 기본 상태를 ACTIVE로 설정

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
