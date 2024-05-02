# 강의
[스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)

# start.spring.io 셋팅
- Project: Gradle - Groovy Project
- Spring Boot: 3.2.4
- Language: Java
- Packaging: Jar
- Java: 17
- Dependencies: Spring Web, Thymeleaf

# 비즈니스 요구사항 (단순하게)
- 데이터: 회원ID, 이름
- 기능: 회원 등록, 조회
- 아직 DB 결정 x -> 우선 인터페이스를 만들고 구현체로는 메모리 기반의 데이터 저장소 사용하는 거로, 향후 DB 결정되면 바꿀 수 있도록