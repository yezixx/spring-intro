package spring.springintro.repository;

import spring.springintro.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); // Map key: id(Long), value: Member(Object)
    private  static Long sequence = 0L; // sequence: 0, 1, 2.. 등 key 값 생성해주는 거
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member의 id 값 세팅
        store.put(member.getId(), member); // store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있으므로 Optional.ofNullable로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // filter: 조건에 맞는 것만 필터링, lambda 사용 (js 화살표 함수 느낌)
                .findAny(); // 하나라도 찾는 거, 결과 자동으로 Optional로 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values());
    }

    public void clearStore(){
        store.clear(); // store 비워줌, test에서 끝날 때마다 비워주기 위함
    }
}
