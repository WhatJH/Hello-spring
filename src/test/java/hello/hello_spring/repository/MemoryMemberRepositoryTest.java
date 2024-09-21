package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

//    MemoryMemberRepository memoryMemberRepository;

    @AfterEach
    // 메소드가 끝날 때마다 동작하게 하는 어노테이션이다. 이것을 통해 객체를 비워주자.
    public void afterEach(){
        repository.clearStore();
//콜백 메서드
    }


    @Test
    public void save(){ //save 함수의 동작 테스트
        //given
        Member member = new Member(); //Member 객체 생성
        member.setName("spring"); //name spring으로 세팅

        //when
        repository.save(member);
//        repository에 생성된 멤버 객체를 save함수에 저장

        //then
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(result.getName(), "spring");
//        Assertions.assertEquals(member).isEqualTo(result);
        System.out.println("result: " + result.equals(member));
//        assertThat(member).isEqualTo(null);
        
//        member에 저장된 id로 findByid함수를 통해 repository에 저장된 Member객체를 꺼내고(get),
//        꺼낸 Member 객체(result)가 save를 통해 저장했던 member와 동일한 객체인지 확인

    }


@Test
public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring1").get();

    assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findById() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}

/**
 * 테스트 유닛을 한번에 테스트하면 error 발생 가능
 * 메모리 DB에 이전 테스트 결과가 남기 때문에
 * 다른 테스트 케이스에 영향이 가지 않게
 * 메모리를 지워야함
 *
 * 테스트는 각각 독립적으로 실행되어야 하며,
 * 테스트의 순서에 의존관계가 있는 테스트는 좋은 테스트가 아님
 */

