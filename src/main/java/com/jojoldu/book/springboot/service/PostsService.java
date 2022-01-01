package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
    스프링에서 Bean을 주입받는 방식 3가지 : @Autowired, setter, 생성자
    가장 권장하는 방식 : 생성자
 */
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해줌
/*
    이렇게 생성자를 직접 안쓰고 롬복 어노테이션을 사용하는 이유!
    해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결할 수 있다(롬복 어노테이션이 있으면 해당 컨트롤러에 새로운 서비스를 추가하거나,
    기존 컴포넌트를 제거하는 등의 상황이 발생해도 생성자 코드는 전혀 손대지 않아도 된다.
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
        update기능에서 DB에 쿼리를 날리는 부분이 없다!!
        가능한 이유 : JPA의 영속성 컨텍스트 때문!
        *영속성 컨텍스트 - 엔티티를 영구 저장하는 환경(일종의 논리적 개념)
        JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈림
        JPA의 엔티티 매니저가 활성화된 상태로(Spring Data Jpa를 쓴다면) 트랜잭션 안에서 DB에서 데이터를 가져오면
        이 데이터는 영속성 컨텍스트가 유지된 상태
        -> 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다!!
        즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다는 것 = 이 개념을 "더티 체킹"이라 한다
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
        // postsRepository.delete(posts) : JpaRepository에서 이미 delete 메소드를 지원하고 있으나 이를 활용
        /*
            엔티티를 파라미터로 삭제할 수도 있고, deleteById메소드를 이용하면 id로 삭제할 수도 있다
            존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제
         */
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    // 트랜잭션 어노테이션에 옵션(readOnly = true)를 주면 '트랜잭션 범위는 유지하되', 조회 기능만 남겨두어 '조회 속도가 개선된다'
    @Transactional(readOnly = true)
    // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환 -> List로 반환하는 메소드
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //.map(posts -> new PostsListResponseDto(posts))를 람다식으로 작성한 것
                .collect(Collectors.toList());
    }
}
