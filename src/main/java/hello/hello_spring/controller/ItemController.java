package hello.hello_spring.controller;

import hello.hello_spring.domain.Item;
import hello.hello_spring.repository.ItemRepository;
import hello.hello_spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemService itemService, @Qualifier("itemRepository") ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }


/**
 * 조회
 */
    @PostMapping(value="/item/list")
    public List<Item> Item() {
//        List<Item> response = itemService.findItems();
//        return response;
        return itemRepository.findAll();
    }


    public Optional<Item> findById(Long itemId) {
        return itemRepository.findById(itemId);
    }



    @PostMapping("/item/new")
    public String Inputitem(@RequestParam("name") String name){
        itemService.save(name);
        return "아이템이 등록되었습니다";
    }


//    @PostMapping("/item/new")
//    public String list(Model model) {
//        model.addAttribute("item", "item value");
//        return "아이템이 등록되었습니다";
//    }
    
    

//    @PostMapping("/item/new")
//    public String save(@RequestParam("name") String name){
//        Item item = new Item();
//        item.setName(item.getName());
//        return "item/list";
//    }



//    @Transactional
//    public void save(String name) {
//        itemRepository.save(Item.toEntity(name));
//    }



//    @GetMapping("/item/new")
//    public String addItem(@ModelAttribute Item item) {
//        this.itemService.save(item);
//        return "forward:/item/list";
//    }




//    @PostMapping("/item")
////    @GetMapping("/item")
//        @RequestMapping("/item")
//    public String list(Model model) {
//        model.addAttribute("item", "item value");
//        return "item/itemList";
//    }


//    @RequestMapping("/item")
//    public String item(Model model){
//        model.addAttribute("key", "item value");
//        return "아이템이 저장되었습니다";
//
//    }




//    @GetMapping("/item")
//    public String itemTest(@RequestParam("name")String name) {
//       itemService.save(name);
//        return "아이템 등록완료";
//    }


//    @PostMapping("/item/new")
//    public String save(@RequestParam("name") String name){
////        itemService.save(name);
////        return "아이템이 등록되었습니다";
//        return save(name);
//    }
    

}
