package spring.springintro.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring.springintro.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();


    // 테스트는 서로 순서와 관계없이 서로 의존관계 없이 설계가 되어야 됨
    // -> 하나의 테스트가 끝날 때마다 저장소나 공용 데이터들 지워줌
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){ // 테스트 순서 3
        // 새로운 멤버 객체 생성
        Member member = new Member();
        member.setName("spring");

        // 리포지토리에 저장
        repository.save(member);

        // 잘 저장되었나 확인하기 위해 findById
        // findById Optional임 -> get()으로 꺼내면 Optional<Member>이 아닌 Member로 받을 수 있음
        Member result = repository.findById(member.getId()).get();

        // new로 만든거랑 DB에서 꺼낸거랑 동일한지 -> 검증
        // 콘솔로 확인하는 방법, 매번 콘솔 확인할 수는 없음
        //System.out.println("result = "+(result == member));

        // Assertions(junit) 사용, assertEquals(기대 값, 실제 값)
        // 동일하면 초록불, 다르면 빨간불
        //Assertions.assertEquals(member, result);

        // Assertions(assertj) 사용, assertThat
        // 더 직관적임. junit의 assertEquals는 기대값과 실제값 헷갈릴 여지 있음
        // static으로 만들어 Assertions 없이 사용할 수 있음
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){ // 테스트 순서 2 -> findAll에서 member1, member2 저장해서 에러남 -> 테스트 끝나고 데이터 클리어 AfterEach
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
    public void findAll(){ // 테스트 순서 1
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
