package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
// memberRepository를 외부에서 받아서 객체가 생성되도록 한다. => DI(Dependency Injection)

    /**
     * 회원 가입
     */

    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x
        vaildateDupricateMember(member); //중복 회원 검증

//optional을 감싸면 여러메서드를 사용할 수 있다
//        과거-> null일 가능성 있으면 옵셔널을 감싼다
// 꺼내고싶으면 get을 사용함, 하지만 꺼내는건 권장하지 않는다.
// 옵셔널을 바로 반환하지 않고, result가 반환되기 때문에 바로 present가 들어간다

        memberRepository.save(member);
        return member.getId();
        //Id반환
    }

    private void vaildateDupricateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
//  Optional로 반환되기에 바로 ifPresint()를 사용할 수 있다  
    
    
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // findOne 한개만 찾는다.
    /**
     * 한 명의 회원 조회
     * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

//서비스는 비즈니스 용어를 사용해야한다(비즈니스처리)
//리포지토리는 기계적, 개발스러운 용어(데이터)



/*
* RestController 해보기~~~
* */

    public List<Member> getUser() {
//        List<Member> list = new ArrayList<>();
//        Member member = new Member();
//        member.setName("박지현");
//        member.setId(11L);

//        Member member2 = Member.toEntity("박지현");

//        list.add(member);

        // select * from user;
        List<Member> list =  memberRepository.findAll();
        log.info("user: {}", list.get(0).getName());
        return list;
    }

    @Transactional
    public void save(String name){
        memberRepository.save(Member.toEntity(name));
    }

}
