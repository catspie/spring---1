package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*@Service*/
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        /* 방법 1
        //같은 이름이 있는 중복 회원 x
        Optional<Member> result = memberRepository.findById(member.getId());
        //optional을 사용하면 ifPresent 메소드를 통해 null체크를 할 수 있다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        //방법2
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     *  메소드 추출 단축키 : ctrl + shift + alt + t
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
    }

    /**
     * 전체 회원 조회
     * 서비스는 비즈니스 의존적으로 네이밍을 한다.
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
