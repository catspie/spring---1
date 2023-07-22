package hello.hello.spring.domain;


import javax.persistence.*;

/**
 *  관계형데이터베이스 : orm
 */
@Entity
public class Member {
    /**
     * 시스템 등록 시 자동으로 id 입력 = 오라클의 시퀀스 = identity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 사용자가 이름 입력 
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
