package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;


//MemberRepository Interface를 구현할 클래스 만들기
//MemberRespository 인터페이스를 상속받는 클래스 생성 인터페이스의 메소드 Override함
//@Repository
public class MemoryMemberRepository implements MemberRepository {
    
//    데이터를 저장할 공간 만들기
    private static Map<Long, Member> store = new HashMap<>(); //stre 변수의 Long자리에 id를, Member자리에 member객체를 넣는다
    private static long sequence = 0L;
    //실무에서는 컨커럼 해쉬를 써야는데 예제라서 단순 해쉬를 쓰겠다.
    //hash 쓰는 이유 :  데이터를 빠르게 찾기 위함


    @Override
//    회원 저장 메소드
    public Member save(Member member) {
        //member 객체 안에 name은 저장되어 넘어온 것이라 보면 됨
        member.setId(++sequence);
//      member 객체의 id에 시퀀스를 1 증가시켜 저장
        store.put(member.getId(), member);
//       Map<Long, Member>타입의 Long자리에 1증가 시킨 시퀀스를
//       Member 자리에 member 객체를 저장한다
        return member; //member 객체 반환
    }

    @Override
    //    id로 회원 찾는 메서드 -> Optional<member>로 반환
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //null이 반환될 가능성이 있으면 optional로 감싸버린다
    }

    @Override
//  name으로 회원 찾는 메서드 -> Optional<Member>로 반환
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나라도 찾는 것
//        member 객체에 있는 name이랑 매개변수로 넘어온 name이 같은지 확인하여
//        일치하는 경우에만 필터링이 됨. 그 중 member 객체를 찾으면 반환됨
//        찾았는데 없으면 Optional에 null이 포함되어 반환된다
        
    }

    @Override
//    모든 회원 List를 조회하는 메서드 -> List<Member>로 반환함
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
//        리스트를 많이 쓴다, values()는 member임
    }
//    업캐스팅(upcasting): 자식 클래스가 부모클래스 타입으로 캐스팅되는 것

    public void clearStore(){
        store.clear();
        //store을 싹 비운다 (stre 변수에 저장되어 있는 데이터를 비운다)
    }
}
