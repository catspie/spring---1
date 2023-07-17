package hello.hello.spring.controller;

import hello.hello.spring.domain.Member;
import hello.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 *  db가 없다면 서버 메모리에 저장되므로 서버 재시작 시 기존 정보 모두 삭제됌
 */
@Controller
public class MemberController {

/*
    객체를 하나만 만들어서 여러 군데서 사용하는 것이 효율적
    bad_ex
    private final MemberService memberService = new MemberService();
*/
    private final MemberService memberService;

/**
 *  스프링컨테이너에서 service, repo, controller인식할 수 있도록 모두 어노테이션 지정
 *  의존관계 주입: di
 */
    /*@Autowired*/
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    /**
     *  회원등록 - 조회
     */
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    /**
     *  회원등록 - 등록
     */
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }
    /**
     *  회원 조회
     */
    @GetMapping("/members")
    public String members(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
