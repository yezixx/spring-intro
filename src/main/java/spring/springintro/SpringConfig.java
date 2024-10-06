package spring.springintro;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.springintro.repository.JpaMemberRepository;
import spring.springintro.repository.MemberRepository;
import spring.springintro.repository.MemoryMemberRepository;
import spring.springintro.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//    private final EntityManager entityManager;
//
//    public SpringConfig(DataSource dataSource, EntityManager entityManager) {
//        this.dataSource = dataSource;
//        this.entityManager = entityManager;
//    }

    private final MemberRepository memberRepository;

    @Autowired // 생성자 하나일 때는 생략해도 됨
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        // return new MemoryMemberRepository(); // 구현체 return, 메모리 교체할 때 여기 수정함
//        // return new JpaMemberRepository(entityManager);
//    }
}
