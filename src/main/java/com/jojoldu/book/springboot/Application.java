package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 스프링 부트의 자동설정, 스프링 Bean읽기와 생성을 모두 자동 설정.
 // SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에
 // Application 클래스는 항상 프로젝트의 최상단에 위치해야 한다.
 @SpringBootApplication
  // @EnableJpaAuditing // JPA Auditing 활성화 - > 제거, 제거 이유!
 // 제거 이유 : @EnableJpaAuditing를 사용하기 위해선 최소 하나의 @Entity클래스가 필요
 // WebMvcTest이다 보니 당연히 없다, @EnableJpaAuditig가 @SpringBootApplication와 함께 있다보니 @WebMvcTest에서도 스캔게 되었다
 // 그래서 @EnableJpaAuditing과 @SpringBootApplication들을 분리한다!
 public class Application { // 앞으로 만들 프로젝트의 main 클래스
    public static void main(String[] args){
        // SpringApplication.run으로 내장 WAS를 실행.
        // -> 이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar(실행가능한 Java 패키징 파일)로 실행하면 된다.
        // 내장WAS 사용의 이점 : 언제 어디서나 같은 환경에서 스프링 부트를 배포 가능
        SpringApplication.run(Application.class, args);
    }
}
