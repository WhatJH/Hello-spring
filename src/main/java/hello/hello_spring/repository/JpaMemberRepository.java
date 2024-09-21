package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
//JPA는 em으로 모든게 동작한다

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }
//persist: 영속, 영구저장하다

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }
//findByName은 jpql이라는 객체지향 쿼리언어를 써야함(sql이랑 비슷함)

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
//        jpql이라는 쿼리 언어, 객체를 대상으로 쿼리를 날린다(엔티티대상)
//        객체 자체를 select한다

    }

/**
pk기반이 아니면 jpql을 작성해줘야한다
jpa기슬을 스프링에 감싸서 제공하는 기술 => spring data jpa 기술
 pk기반 아닌 것도 jpql을 안 짜도 된다 !
 주의점 : 항상 트랜잭션이 있어야한다
 join 들어올 때 모든 데이터변경이 트랜잭션 안에서 실행되어야한다.
 */

}