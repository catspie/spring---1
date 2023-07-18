package hello.hello.spring;
import hello.hello.spring.repository.JdbcMemberRepository;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import hello.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * (구버전) xml > 자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration
public class SpringConfig {

    /**
     *  스프링으로 db 연결 
     */
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        // 서버에 데이터 저장
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}