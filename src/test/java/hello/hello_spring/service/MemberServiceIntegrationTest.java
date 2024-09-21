package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

//@Commit을 해줘야지 DB에 반영된다

    @Test
//    @Transactional
    void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName ("spring100");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberRepository.findById(saveId).get();
//        Assertions.assertThat(findMember.getName()).isEqualTo("hello"); // testcode 끝에는 비교, 비어있지 않은지, ,,
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    @Transactional
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 에러 터트릴떄 : throw new IllegalException~~ -> 에러가 터짐
        // new NullPointerException


//        memberService.join(member2);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () ->  memberService.join(member2)); //여기서 예외가 터져야한다.

//        System.out.println(e.getMessage());


//        Assertions.assertThat(IllegalStateException.class, () -> memberService.join(member2));
//        Assertions.assertEquals(e.getMessage(), ("이미 존재하는 회원입니다."));

//        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers(){
    }

    @Test
    void findOne(){
    }
}

//test는 한글로 바꿔도 괜찮다 join -> 회원가입/ 실제 동작하는 코드들은 한글로 적기 애매하다.
// 빌드될 때 테스트 코드는 실제 코드에 포함되지 않는다.

