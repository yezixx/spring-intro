package spring.springintro.repository;

import spring.springintro.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    /*
    Optional이란? (Java 8애 들어가있는 기능)
    findById나 findByName의 경우 가져올 때 null 일 수 있음
    -> null을 처리할 때 null을 그대로 반환하는 방법 대신 Optional로 감싸서 반환하는 방법 선호
     */
    List<Member> findAll();
}
