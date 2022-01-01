package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;
import java.io.Serializable;

@Getter
// SessionUser에는 인증된 사용자 정보만 필요, 그 외에 필요한 정보들은 없으니 name, email, picture만 필드로 선언
public class SessionUser implements Serializable{ 
    private String name;
    private String email;
    private String picture;

    /*
        왜 User클래스를 그대로 가져다 사용하지 않았을까?
        -> 세션에 저장하기 위해 User클래스를 세션에 저장하려고 하니, User클래스에 직렬화를 구현하지 않았다는 오류 발생
        그럼 오류 해결위해 User클래스에 직렬화 코드를 넣으면 되나? -> User클래스는 엔티티이기 때문에 생각해야할 요소가 많다
        엔티티 클래스에는 언제 다른 엔티티와 관계가 형성될지 모른다. 
        ex) @OneToMany, @ManyToMany 등 자식 엔티티를 갖고있다면 직렬화 대상에 자식들까지 포함되니 '성능 이슈, 부수 효과'
        가 발생할 확률이 높다. 그래서 직렬화 기능을 가진 Dto를 하나 추가로 만드는 것이 운영 및 유지보수면에서 많은 도움이 된다
     */
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
