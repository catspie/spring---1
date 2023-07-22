package hello.hello.spring;
import hello.hello.spring.aop.TimeTraceAop;
import hello.hello.spring.repository.*;
import hello.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.sql.Time;

/**
 * (구버전) xml > 자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *  스프링으로 db 연결 
     */
    /*private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }*/

    /*@Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository() {
//        // 서버에 데이터 저장 return new MemoryMemberRepository();
//        // 순수 jdbc return new JdbcMemberRepository(dataSource);
//        // jdbctemplate return new JdbcTemplateMemberRepository(dataSource);
//        // jpa return new JpaMemberRepository(em);
//    }

/*    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/
}