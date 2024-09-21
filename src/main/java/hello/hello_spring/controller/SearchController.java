package hello.hello_spring.controller;


import hello.hello_spring.domain.Member;
import hello.hello_spring.service.ItemService;
import hello.hello_spring.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final MemberService memberService;

    public SearchController(MemberService memberService, ItemService itemService) {
        this.memberService = memberService;
    }


    @GetMapping("/user")
    public List<Member> getUser() {
        List<Member> response = memberService.getUser();
        return response;
    }

    // /user?name=박지현
//    [{id : 1 , name : "박지현"}]
// {status : 401 , message:"해당 유저가 존재하지 않습니다", data: null}

    // /user/add?name="박지현2"
    @GetMapping("/user/add")
    public String saveTest(@RequestParam("name") String name) {
        memberService.save(name);
        return "저장완료";
    }

}




//    public String search() {
//        return searchController.search();
//    }

