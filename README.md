# Simple Board Service 📝

MyBatis 기반으로 구현한 간단한 게시판 웹 애플리케이션입니다.  
회원 관리와 게시글 CRUD 기능을 제공하며, 보안과 안정성에 중점을 두었습니다.

---

## 🔧 기술 스택
- openjdk-22.0.2
- Spring Boot 3.5.5 (Spring Web, Spring Security, MyBatis)
- H2 Database
- Thymeleaf
- Gradle

---

## 📌 주요 기능
- 회원가입 / 로그인 (비밀번호 암호화)
- 게시글 CRUD (작성, 조회, 수정, 삭제)
- 작성자만 수정/삭제 가능
- 페이징 및 검색 기능
- MyBatis XML 기반 매퍼 사용

---

## 🗂️ 실행 방법
```bash
./gradlew bootRun
