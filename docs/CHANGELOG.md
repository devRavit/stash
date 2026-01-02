# Changelog

프로젝트 변경 내역 (최신순)

<!-- CHANGELOG_START -->

## v0.1.1
`2026.01.03 02:00`

Google Calendar API 연동 기능 추가

- Google Calendar API v3 연동으로 캘린더 이벤트 생성 기능 구현
- Domain Layer: CalendarClient 인터페이스 및 DTO (CalendarEventRequest/Response)
- Infrastructure Layer: GoogleCalendarClient 구현 (Access Token → GoogleCredentials 변환)
- Service Layer: CalendarService 비즈니스 로직 조율
- Controller Layer: POST /api/calendar/events 엔드포인트 (Authorization 헤더로 Access Token 전달)
- DateTimeUtils: EventDateTime ↔ LocalDateTime 변환 유틸리티 (extension function pattern)
- CalendarException: sealed class 기반 예외 처리 (InvalidAccessToken, CalendarApi, EventCreationFailed)
- GlobalExceptionHandler: HTTP 상태 코드 매핑 (401, 502, 500)
- EventBuilder DSL: Type-safe builder 패턴으로 Event 객체 생성 (private constructor + invoke operator)
- CORS 설정: localhost:3000, ravit.run 허용
- Stateless 설계: Access Token을 매 요청마다 전달, DB 저장 없음

---

## v0.1.0
`2026.01.02 21:30`

Gemini API 기반 AI 기능 통합

- Koog agents 프레임워크 기반 AI 통합 (음성-텍스트, 캘린더 자동화 등 활용 예정)
- Google Gemini API 클라이언트 구현 (GeminiClient)
- AI 기능 테스트용 엔드포인트 추가 (`POST /api/ai/chat`)
- HTTP 요청을 위한 Ktor HttpClient 설정
- API 인증을 위해 x-goog-api-key 헤더 사용
- 디버그 로그에서 민감정보 제거
- 의존성 업데이트: Ktor 3.2.3, Koog agents 0.6.0
- gemini-2.5-flash 모델 사용

---

## v0.0.11
`2025.11.30 14:25`

기본 프로파일을 local로 설정

- 로컬 개발 시 MongoDB Atlas 연결을 위해 local 프로파일 기본 활성화
- prod 배포 시 환경변수로 프로파일 오버라이드

---

## v0.0.10
`2025.11.30 04:00`

Health Check 확장성 개선

- `DependencyHealthIndicator` 인터페이스 도입으로 확장 가능한 health check 구조
- MongoDB replica set 노드 상태 모니터링 (PRIMARY/SECONDARY/ARBITER)
- UP/DEGRADED/DOWN 3단계 상태 체계 도입
- Critical dependency 구분 (DB down = 서비스 DOWN, 비critical = DEGRADED)
- `MongoClient.clusterDescription` 활용하여 Atlas 무료티어 호환

---

## v0.0.9
`2025.11.29 23:00`

배포 워크플로우에서 하드코딩 제거

- GitHub Actions에서 ECR URL이 secret으로 마스킹되는 문제 해결
- deployment-hub에서 직접 ECR image URI 구성
- `project`, `ECR_REPOSITORY` 하드코딩을 `github.event.repository.name`으로 동적 처리

---

## v0.0.8
`2025.11.29 22:45`

CI/CD workflow 분리

- CI workflow 성공 후 Deploy workflow 실행되도록 변경
- `workflow_run` 이벤트로 workflow 연결
- main push 시 중복 실행 제거

---

## v0.0.7
`2025.11.29 22:30`

AWS Secrets Manager 동적 주입 지원

- MongoDB URI를 환경변수에서 Secrets Manager 주입 방식으로 변경
- `spring.mongodb.uri` → `SPRING_MONGODB_URI` 환경변수로 자동 변환
- production 환경에서 deployment-hub를 통한 secret 주입

---

## v0.0.6
`2025.11.29 22:00`

Health 엔드포인트와 Status 모니터링 엔드포인트 분리

- `/health`: AWS 헬스체크용 단순 "OK" 응답
- `/internal/status`: 상세 상태 모니터링 (버전, DB 연결 상태)
- `StatusInternalController` 추가
- `BuildProperties`를 활용한 버전 정보 노출

---

## v0.0.5
`2025.11.29 17:30`

MongoDB Atlas 연동 및 CI/CD 개선

- MongoDB Atlas 클러스터 연결 설정
- local/prod 환경별 프로필 분리
- MongoConfiguration 추가 (connection pool, socket, cluster 설정)
- GitHub Deployments 연동 (배포 상태 표시)
- 배포 성공 시 자동 Release 생성 (v태그)

---

## v0.0.4
`2025.11.27 00:00`

Spring Boot 4.0 호환성 및 배포 개선

- Jackson write-dates-as-timestamps 옵션 제거
- Graceful shutdown 설정 추가 (30초 타임아웃)
- ECR 이미지 태그를 버전 기반으로 변경

---

## v0.0.3
`2025.11.26 23:00`

AWS App Runner CI/CD 파이프라인 구축

- Docker 컨테이너 이미지 빌드 설정
- AWS ECR/App Runner 서비스 생성
- GitHub Actions CI/CD 워크플로우 추가

---

## v0.0.2
`2025.11.26 21:00`

Kotlin 2.2.21 및 JDK 24 마이그레이션

- Kotlin 2.1.0 → 2.2.21 업그레이드
- JDK 25 → 24 다운그레이드 (Kotlin 호환성)
- ktlint 스타일 수정

<!-- CHANGELOG_END -->