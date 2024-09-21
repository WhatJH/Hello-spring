package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

//@Repository
public interface MemberRepository {
//인터페이스를 상속받는 클래스에서는 메소드들을 Override하여 비즈니스 로직을 구체화한다.

//    회원을 저장하는 메서드
    Member save(Member member);

//  id or name으로 회원정보를 가져오는 메서드
    // select * from user where userId = ?;
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
//    회원 정보를 전체 List를 가져오는 메서드
}

/**
 * Optional이란?
 * 반환되는 값이 Null일 경우
 * null을 그대로 반환하지 않고 Oprional로 감싼다
 * 요즘은 Optional로 데이터를 감싸는 방식을 선호함
 */
