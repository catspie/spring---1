package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 *  테스트코드 자동완성 단축키: ctrl + shift + t
 */
@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {

    /**
     *  테스트 작성 특징
     *  - 함수 이름 직관적인 한글로 지정 가능
     *  - given-when-then 문법
     */
    @Autowired MemberService memberService;
    //@Autowired MemoryMemberRepository memberRepository;
    @Autowired
    MemberRepository memberRepository;

    /**
     *   di : 외부에서 memberRepository를 가져와 memberService에 넣어주는것
 2    */
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    /**
     *  데이터 쌓이지 않도록 afterEach 메소드로 clear
     */
/*
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
*/
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /* 방법1
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then
    }
}