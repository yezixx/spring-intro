package spring.springintro.repository;

import jakarta.persistence.EntityManager;
import spring.springintro.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 리턴값 없음
        return member; // 리턴 타입에 맞추기 위함
    }

    @Override
    public Optional<Member> findById(Long id) {
        em.find(Member.class, id);
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        // name의 경우에는 JPQL 라는 객체 지향 쿼리 언어 사용해야 됨
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // Member 엔티티를 조회, 멤버 엔티티 자체를 select
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
