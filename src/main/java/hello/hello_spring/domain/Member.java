package hello.hello_spring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class Member {

    //PK, 아이덴티티 전략, DB가 알아서 생성해주는 것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //시스템이 정하는 임의의 값
    private String name; //고객의 이름
//    private int age;
//  private로 id와 name 생성한다 -> 외부에서 함부로 변경하면 안 되는 값이므로



    // getter setter 생성한다 -> get, set을 통해 id, name에 값을 넣기, 가져오기 위함

    //    @Column(name = "username"), DB에 username이 매핑이 된다
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    // 정적 팩토리 메서드 패턴
    public static Member toEntity(String name){
        return Member.builder()
                .name(name)
                .build();
    }


}
    /**
     * RestController 해보기
     *
     */

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//}
//
//    }
//    public void getUser(int age, String name) {
//        this.age = age;
//        this.name = name;
//    }
//}