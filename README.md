# Spring Gift JPA 🎁

## 프로젝트 소개

이 프로젝트는 **카카오테크캠퍼스**에서 Spring Boot와 JPA를 학습하기 위해 개발된 **상품 관리 및 위시리스트 서비스**입니다.

> ⚠️ **주의**: 이 프로젝트는 학습 목적으로 개발되었으며, 실제 운영 환경에서의 사용을 위해서는 추가적인 보안 강화와 기능 개선이 필요합니다.

## 주요 기능

### 🔐 회원 관리
- 회원 가입/로그인
- JWT 토큰 기반 인증
- BCrypt를 이용한 비밀번호 암호화

### 📦 상품 관리
- 상품 등록, 수정, 삭제
- 상품 목록 조회 (페이징 처리)
- 상품명 유효성 검증 ("카카오" 포함 시 제한)

### ❤️ 위시리스트
- 위시리스트에 상품 추가/삭제
- 개인별 위시리스트 조회 (페이징 처리)
- JWT 토큰을 통한 사용자 인증

## 기술 스택

### Backend
- **Java 21**
- **Spring Boot 3.3.1**
- **Spring Data JPA**
- **Spring Security** (암호화)
- **H2 Database** (인메모리)
- **JWT** (JSON Web Token)

### Frontend
- **Thymeleaf**
- **HTML/CSS/JavaScript**
- **jQuery**

## 아키텍처

```
src/
├── main/
│   ├── java/gift/
│   │   ├── config/         # 설정 클래스
│   │   ├── controller/     # REST API 컨트롤러
│   │   ├── dto/           # 데이터 전송 객체
│   │   ├── entity/        # JPA 엔티티
│   │   ├── exception/     # 커스텀 예외
│   │   ├── filter/        # JWT 필터
│   │   ├── handler/       # 글로벌 예외 처리
│   │   ├── repository/    # JPA 리포지토리
│   │   ├── service/       # 비즈니스 로직
│   │   └── util/          # 유틸리티 클래스
│   └── resources/
│       ├── templates/     # Thymeleaf 템플릿
│       ├── static/        # 정적 리소스
│       └── application.properties
└── test/                  # 테스트 코드
```

## 학습 과정

### 0단계 - 프로젝트 기본 구조
- Spring Boot 프로젝트 생성
- 기본적인 상품 관리 기능 구현
- Wishlist 기능에 JPA 적용

### 1단계 - JPA 엔티티 설계
- `Member`, `Product`, `Wishlist` 엔티티 생성
- JPA 관계 매핑 설정
- `@DataJpaTest`를 활용한 학습 테스트 작성

### 2단계 - Repository 패턴 적용
- 기존 DAO → Spring Data JPA Repository로 전환
- SQL 쿼리 → JPA 메서드로 변경
- 엔티티 간 연관관계 매핑 구현

### 3단계 - 페이징 및 고도화
- `Pageable` 객체를 활용한 페이징 처리
- 상품 목록 및 위시리스트 페이징 적용
- 트랜잭션 관리 및 코드 리팩토링

## 실행 방법

### 1. 프로젝트 클론
```bash
git clone https://github.com/eunsoni/spring-gift-jpa.git
cd spring-gift-jpa
```

### 2. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 3. 브라우저에서 접속
```
http://localhost:8000
```

### 4. H2 콘솔 접속 (개발용)
```
http://localhost:8000/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (비어있음)
```

## API 엔드포인트

### 회원 관리
- `POST /members/register` - 회원 가입
- `POST /members/login` - 로그인

### 상품 관리
- `GET /api/admin/products` - 상품 목록 조회
- `POST /api/admin/products/add` - 상품 등록
- `POST /api/admin/products/edit/{id}` - 상품 수정
- `GET /api/admin/products/delete/{id}` - 상품 삭제

### 위시리스트
- `GET /api/wishlist` - 위시리스트 조회
- `POST /api/wishlist/{productId}` - 위시리스트 추가
- `DELETE /api/wishlist/{productId}` - 위시리스트 제거

## 주요 학습 내용

### JPA 활용
- 엔티티 설계 및 관계 매핑
- Spring Data JPA Repository 패턴
- JPQL과 페이징 처리
- 트랜잭션 관리

### Spring Boot 아키텍처
- 3-tier 아키텍처 (Controller-Service-Repository)
- 의존성 주입 (Constructor Injection)
- AOP를 활용한 트랜잭션 관리

### 보안
- JWT 토큰 기반 인증
- BCrypt 비밀번호 암호화
- 커스텀 JWT 필터 구현

## 개선 사항 (리팩토링 완료)

- ✅ **의존성 주입 개선**: `@Autowired` → 생성자 주입 방식으로 변경
- ✅ **비밀번호 보안 강화**: 평문 저장 → BCrypt 암호화 적용
- ✅ **트랜잭션 관리**: Service 계층에 `@Transactional` 추가
- ✅ **버그 수정**: Product 엔티티의 `edit` 메서드 파라미터 오류 수정
- ✅ **위시리스트 기능 보완**: 위시리스트 추가 API 구현

## 향후 개선 방향

### 보안 강화
- [ ] Spring Security 전면 도입
- [ ] CORS 정책 설정
- [ ] API Rate Limiting

### 기능 확장
- [ ] 상품 카테고리 관리
- [ ] 상품 이미지 업로드
- [ ] 사용자별 권한 관리 (ADMIN/USER)
- [ ] 상품 검색 기능

### 인프라
- [ ] MySQL/PostgreSQL 등 실제 DB 연동
- [ ] Docker 컨테이너화
- [ ] 프로파일별 설정 분리

### 테스트
- [ ] 통합 테스트 확대
- [ ] Service 계층 단위 테스트
- [ ] API 테스트 자동화

## 라이선스

이 프로젝트는 학습 목적으로 개발되었습니다.

---

**개발자**: [eunsoni](https://github.com/eunsoni)  
**개발 기간**: 카카오테크캠퍼스 과정 중  
**학습 목적**: Spring Boot + JPA 기술 스택 학습


