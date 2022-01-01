package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
    어노테이션 순서를 주요 어노테이션을 클래스에 가깝게 두면
    이후에 변경점이 생겼을시 쉽게 삭제하는 등 수정이 편하다
 */
// Getter,NoargsConstructor : 롬복의 어노테이션
@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor // 기본 생성자 자동추가, public Posts() {}와 같은 효과
@Entity // JPA의 어노테이션, 테이블과 링크될 클래스임을 나타낸다, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다
public class Posts extends BaseTimeEntity { // Posts클래스 : 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity클래스라고도 한다
                    // JPA를 사용하면 DB데이터에 작업할 경우 실제 쿼리를 날리기보단, 이 Entity클래스의 수정을 통해 작업한다
    @Id // 해당 테이블의 PK필드를 나타낸다
    // GeneratedValue : PK의 생성규칙,
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column : 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 사용하는 이유 : 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    // ex) 문자열의 경우 VARCHAR(255)가 기본값, 사이즈를 500으로 늘리고 싶거나(ex:title), 타입을 TEXT로 변경하고 싶거나(ex:content)등의 경우 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 빌드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
