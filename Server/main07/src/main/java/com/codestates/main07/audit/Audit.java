package com.codestates.main07.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 엔티티 객체를 생성, 수정 및 삭제할 때 Spring Data JPA가 생성 시간, 수정 시간 및 삭제 시간을 자동으로 관리합니다.
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;
}

/*------------------------------------------------
  각 엔티티 클래스에서 사용할 시
  Audit 클래스를 엔티티 클래스에서 상속 받아 사용합니다.

  @Entity
  public class Member extends Audit {
  }
----------------------------------------------------
  그리고 메인 클래스(Main07Application)에
  @SpringBootApplication 위에
  @EnableJpaAuditing 어노테이션을 추가했습니다.
 ---------------------------------------------------*/

