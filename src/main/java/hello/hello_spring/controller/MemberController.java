package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {

    private final MemberService memberService ;
    //new로 생성해서 사용할 수도 있다, 문제점: 다른 컨트롤러가 멤버서비스를 가져다 쓸 수 있다.
    // 객체를 new하면 별기능이 없다, 하나 생성해놓고 공용으로 쓰면된다



//연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService; //전역변수 선언해서 memberService 사용하겠음
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //post -> data를 form 같은 곳에 넣어서 전달할 때 사용, get -> 조회
    @PostMapping("/members/new")
    public String create(MemberForm form){ //form에서 받아온 이름이 form객체로 들어감
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";

    }
    
//    전체회원조회
//    모델에 전체 회원리스트를 담아 template로 넘겨준다
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); //
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
