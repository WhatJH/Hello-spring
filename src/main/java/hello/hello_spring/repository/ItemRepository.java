package hello.hello_spring.repository;

import hello.hello_spring.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    Item save(Item item);
//
//    Optional<Item> findById(Long id);
////    Optional<Item> findByName(String name);
//    List<Item> findAll();
}
