package hello.hello.spring;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import hello.hello.spring.service.MemberService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
/**
 * (구버전) xml > 자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}