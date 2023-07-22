package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository{
    //스프링에서 권장하며 코드가 가장 축약된 방법
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate
                .query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate
                .query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }
    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setId(rs.getLong("name"));
                return member;
            };
        }
    }

