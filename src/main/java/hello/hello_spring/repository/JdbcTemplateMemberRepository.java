//package hello.hello_spring.repository;
//
//import hello.hello_spring.domain.Member;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//public class JdbcTemplateMemberRepository implements MemberRepository{
//
//    private final JdbcTemplate jdbcTemplate;
//
////생성자 하나면 스프링 빈으로 등록되면 Autowired 생략가능
////    @Autowired
//    public JdbcTemplateMemberRepository(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//
//    @Override
//    public Member save(Member member) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("name", member.getName());
////이렇게 해서 넣으면 쿼리를 짤 필요가 없다, 이 코드 자체가 insert문을 만들어 준다. (document 참고)
//
//        Number Key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setId(Key.longValue());
//        return member;
//    }
//
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id);
//        return result.stream().findAny();
//    }
////Jdbc랑 비교해서 보면 간단하다,
//// 디자인패턴-template
//
//    @Override
//    public Optional<Member> findByName(String name) {
//        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
//        return result.stream().findAny();
//    }
//
//
//    @Override
//    public List<Member> findAll() {
//        return jdbcTemplate.query("select * from member ", memberRowMapper());
//    }
//
//
//    private RowMapper<Member> memberRowMapper() {
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getLong("id"));
//            member.setName(rs.getString("name"));
//            return member;
////      객체 생성에 대한 건 여기서 콜백으로 정의가 된다.
//        };
//    }
//}
