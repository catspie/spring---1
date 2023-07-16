package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
/*import org.junit.jupiter.api.Assertions;*/
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 테스트 순서는 무작위, 테스트간 의존관계 x > 테스트 끝난 후 clear 필요
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    /**
     * member 저장 기대값과 실행값 비교
     */
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        /**
         *  optional에서 값을 꺼낼때는 get을 사용한다.
         */
        Member result = repository.findById(member.getId()).get();
        /*Assertions.assertEquals(result, member);*/
        /*Assertions.assertThat(member).isEqualTo(result);*/
        /*클래스를 추가해 간략하게 표현가능*/
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
