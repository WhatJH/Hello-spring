package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Transactional
public class MemberServiceTest {

    MemberService memberService;
    // 해당 레포지토리 니가짓고싶은이름
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void boforeEach() {
    memberRepository  = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName ("hello");

        //when
        Long saveId = memberService.join(member); // -> 1번 hello ~~~~

        //then
       // Optional<Member> one = memberService.findOne(saveId).get();
        // select * from user where id = 1; -> Member.getName => "hello"
        Member findMember = memberRepository.findById(saveId).get();
        Assertions.assertThat(findMember.getName()).isEqualTo("hello"); // testcode 끝에는 비교, 비어있지 않은지, ,,

    }


    @Test
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
        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class,
                () ->  memberService.join(member2)); //여기서 예외가 터져야한다.

//        Assertions.assertThat(IllegalStateException.class, () -> memberService.join(member2));
//        Assertions.assertEquals(e.getMessage(), ("이미 존재하는 회원입니다."));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//        }

        //then
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