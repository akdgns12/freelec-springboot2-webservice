package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;

/*
   JUnit의 기본 assertThat이 아닌 assertj의 assertThat을 사용.
   JUnit과 비교하여 assertj의 장점
   - CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음(JUnit의 assertThat을 쓰게 되면 is()와 같이 CoreMatcher 라이브러리가 필요)
   - 자동완성이 좀 더 확실하게 지원됨(IDE에서는 CoreMatchers와 같은 Mathcer 라이브러리의 자동완성 지원이 약함)
*/
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);


        // then
        assertThat(dto.getName()).isEqualTo(name); // assertj라는 테스트 검증 라이브러리의 검증 메소드, 검증하고 싶은 대상을 메소드 인자로 받는다
                                                    // isEqualTo : assertj의 동등 비교 메소드, assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을때만 성공
        assertThat(dto.getAmount()).isEqualTo(amount); 
    }

}
