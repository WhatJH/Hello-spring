package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //클래스명 앞에 등록하면 스프링이 설정 파일임을 인식, 컨테이너에 스프링 빈을 등록할 준비
public class SpringConfig {

/**
 * Bean 사용하는 클래스의 경우 반드시 Configuration을 같이 사용
 *     Bean 수동으로 Bean 객체 생성 */
    
//    @PersistenceContext
    private final MemberRepository memberRepository;
//    private final EntityManager em;
//    private final DataSource dataSource;
    
/**   회원 서비스는 한 번만 생성되어 생성된 하나의 회원 서비스 인스턴스를 각각의 컨트롤러들이 공유함 */

//
//    public SpringConfig(EntityManager em, DataSource dataSource) {
//        this.em = em;
//        this.dataSource = dataSource;
//    }

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Autowired
//    private SpringConfig(EntityManager em) {
//        this.em = em;
//    }


//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JpaMemberRepository(em);


    /**
     *
     * RestController 동작해보기~
     */


}


/**
 * 스프링 빈으로 자동 등록하는 방법
 * Controller
 * Service
 * Repository
 * 이 세가지? -> Component로 대체 가능함
 * 
 * 
 * DI의 3가지 방법
 * 1. Field 주입 - 멤버 변수 앞에 @Autowired 어노테이션을 붙임
 * 2. Setter 주입 - setter가 public으로 설정되어있어 문제가 생길 가능성이 있음
 * 3. 생성자 주입 - 조립 시점에 생성자를 한 번만 호출하여 컨테이너에 스프링 빈을 등록하고,
 *                 이후에는 의존관계가 동적으로 변경되는 경우가 거의 없기 때문에 생성자 주입 권장
 */

