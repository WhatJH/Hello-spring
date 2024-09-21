package hello.hello_spring.service;

import hello.hello_spring.domain.Item;
import hello.hello_spring.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
//@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;


    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Long save(String item) {


        return itemRepository.save(
                Item.builder()
                        .name(item)
                        .build()).getId();
    }



    public List<Item> findItems(){
        return itemRepository.findAll();
    }


//    @Transactional
//    public void save(Item item){
////        itemRepository.save(item);
//        this.itemRepository.save(item);
//    }

//// 한개만 찾을거임
//    public Optional<Item> findOne(Long itemId){
//        return itemRepository.findById(itemId);
//    }


}
