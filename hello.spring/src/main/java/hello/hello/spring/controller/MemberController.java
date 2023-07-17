package hello.hello.spring.controller;

import hello.hello.spring.domain.Member;
import hello.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
