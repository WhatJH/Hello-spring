package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

//   JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);


    // select * from Member where name = ?;
//    @Query("select m from Member m where m.name = :username")
//    Optional<Member> findByUserName(String username);

}

// SpringDataJpa가 Jpa 리포지토리를 받고있으면
//구현체를 자동으로 만들어서 스프링빈에 자동 등록해준다.


