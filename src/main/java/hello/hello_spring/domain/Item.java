package hello.hello_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Item {


//    private Long id;
//    private String name;
//    private int price;
//    private int Count;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
//    private String price;


    public void response(Long id, String name, String price) {
        this.id = id;
        this.name = name;
//        this.price = price;

    }

    public void getId(Long id){
        this.id = id;
    }

        public void getName(String name){
        this.name = name;
    }


//    public void setPrice(String price){
//        this.price = price;
//    }


//    public static Item toEntity(Item item){
//        return Item.builder()
//                .name(name)
//                .build();
//    }
}
