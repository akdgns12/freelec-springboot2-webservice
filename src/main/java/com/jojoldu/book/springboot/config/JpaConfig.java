package com.jojoldu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing 활성화
/*
   제거 이유 : @EnableJpaAuditing를 사용하기 위해선 최소 하나의 @Entity클래스가 필요
   WebMvcTest이다 보니 당연히 없다, @EnableJpaAuditig가 @SpringBootApplication와 함께 있다보니 @WebMvcTest에서도 스캔게 되었다
   그래서 @EnableJpaAuditing과 @SpringBootApplication들을 분리한다!
 */
public class JpaConfig {
}
