package spring.springintro.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.springintro.domain.Member;
import spring.springintro.repository.MemberRepository;
import spring.springintro.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService/* = new MemberService()*/;
    // db클리어 해줘야하는데 서비스밖에 없음 -> 리포지토리 가져옴
    MemoryMemberRepository memberRepository/* = new MemoryMemberRepository()*/;
    // 리포지토리 테스트 코드의 리포지토리와 위의 리포지토리는 다른 객체(new로 각각 생성됐으니)
    // 멤버서비스 클래스의 메모리멤버리포지토리랑 지금 테스트의 메모리멤버리포지토리가 서로 다른 인스턴스
    // 리포지토리 클래스의 HashMap이 static이 아니면 서로 다른 db가 되면서 문제가 생길 수 있음
    // 그리고 그냥 같은 거로 테스트하는 게 맞음
    // -> 멤버서비스에서 new Memory~~ 지우고 Constructor로 받자
    // -> 테스트에서는 BeforeEach로 설정

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 각 테스트 실행 전에 리포지토리 만들어서 서비스에 넣어줌
    }

    // 테스트는 서로 순서와 관계없이 서로 의존관계 없이 설계가 되어야 됨
    // -> 하나의 테스트가 끝날 때마다 저장소나 공용 데이터들 지워줌
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given - 이런 상황이 주어졌고
        Member member = new Member();
        member.setName("spring");

        // when - 그거를 실행했을 때
        Long saveId = memberService.join(member);
        // 우리가 저장을 한 게 리포지토리에 있는게 맞는지 찾고싶음 -> 리포지토리 꺼내야됨

        // then - 결과가 이렇다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 테스트는 예외상황 체크하는 게 중요함 -> 중복 회원 검증 로직에서 예외가 잘 처리되는가
    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        /*try{
            memberService.join(member2);
            fail(); // 예외가 잘 발생했다면 이거 실행되면 안됨
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 다르면 실패
        }*/
        IllegalStateException e = assertThrows(IllegalStateException.class/*이 예외가 발생하길 기대함*/, () -> memberService.join(member2) /*이 로직을 실행할 때*/);
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 에러 메시지 받아서 assertThat 가능


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}