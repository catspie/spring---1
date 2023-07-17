package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.*;

/*@Repository*/
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    /**
     * 회원 저장
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    /**
     * id로 회원 찾기
     * optional의 null체크 사용
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    /**
     * name으로 회원 찾기
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values()
                .stream().filter(member -> member.getName().equals(name))
                .findAny();
    }
    /**
     * 전체 회원 찾기
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
