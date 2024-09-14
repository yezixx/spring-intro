package spring.springintro.service;

import spring.springintro.domain.Member;
import spring.springintro.repository.MemberRepository;
import spring.springintro.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*회원 가입*/
    public Long join(Member member){
        // 같은 이름의 중복회원 가입 방지
        //Optional<Member> result = memberRepository.findByName(member.getName());
        /*result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        // optional로 반환됨 -> 옵셔널의 메소드들 사용가능
        // optional이 아니였다면 if(result != null) 이렇게 썼을 것
        // null일 가능성이 있으면 optional로 감싸서 반환받아 여러 메소드 사용
        // 그냥 값 꺼내고 싶으면 result.get()하면 됨, 권장하지는 않음
        // orElseGet()을 많이 씀, 값이 있으면 꺼내고 없으면 별도의 처리 가능
        // 옵셔널로 꺼내는 거 좀 안이쁜데? -> memberRepository.findByName(member.getName())의 반환값이 optional이니 바로 ifPresent 실행
        /*memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });*/
        validateDuplicateMember(member); // 중복회원 검증
        // 뭔가 로직이 쭉 나옴 -> 별도의 메소드로 뽑는 게 좋음 -> ctrl+alt+shift+t 에서 extract method 또는 ctrl+alt+m

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
