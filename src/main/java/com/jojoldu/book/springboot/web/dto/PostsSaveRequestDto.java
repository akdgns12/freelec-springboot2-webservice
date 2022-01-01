package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
/*
    Entity클래스와 거의 유사한 형태임에도 Dto클래스를 추가로 생성했다.
    하지만, 절대로 Entity클래스를 Request/Response 클래스로 사용해서는 안된다
    -> Entity클래스는 DB와 맞닿은 핵심 클래스! Entity클래스를 기준으로 테이블이 생성되고, 스키마가 변경된다.
    ex) 화면변경은 아주 사소한 기능 변경인데, 이를 위해 테이블과 연결된 Entity 클래스를 변경하는 것은 너무 큰 변경
 */
// Entity클래스가 변경되면 여러 클래스에 영향을 미치지만, Request와 Response용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요
// View Layer와 DB Layer의 역할 분리를 철저히 하는게 좋다
// -> ex) 실제로 Controller에서 결괏값으로 여러 테이블을 조인해서 줘야할 경우가 많아 Entity클래스만으로 표현하기가 어려운 경우가 많다
// 즉, Entity클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 한다
public class PostsSaveRequestDto { // Controller와 Service에서 사용할 Dto클래스

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                        .title(title)
                                .content(content)
                                        .author(author).
                build();
    }
}
