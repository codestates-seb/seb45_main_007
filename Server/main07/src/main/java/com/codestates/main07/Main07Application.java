package com.codestates.main07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @@EnableJpaAuditing
 * @@CreatedDate: 엔터티가 처음 저장될 때의 시간을 자동으로 설정합니다.
 * @@LastModifiedDate: 엔터티가 수정될 때마다 현재 시간을 자동으로 설정합니다.
 * @@CreatedBy: 엔터티가 처음 저장될 때의 사용자 정보를 자동으로 설정합니다. (실행 컨텍스트에서 제공하는 사용자 정보를 이용)
 * @@LastModifiedBy: 엔터티가 수정될 때의 사용자 정보를 자동으로 설정합니다.
 * @사용법: 이러한 auditing 기능을 사용하기 위해서는: 메인 애플리케이션 클래스에 @EnableJpaAuditing 애너테이션을 추가하여 auditing 기능을 활성화해야 합니다.
 */

@SpringBootApplication
public class Main07Application {

	public static void main(String[] args) {
		SpringApplication.run(Main07Application.class, args);
	}

}
