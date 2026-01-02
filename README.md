# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

[![v0.1.1](https://img.shields.io/badge/v0.1.1-purple)](./docs/CHANGELOG.md#v011) `2026.01.03 02:00`
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

[![v0.1.0](https://img.shields.io/badge/v0.1.0-gray)](./docs/CHANGELOG.md#v010) `2026.01.02 21:30`
- Koog agents 프레임워크 기반 AI 통합 (음성-텍스트, 캘린더 자동화 등 활용 예정)
- Google Gemini API 클라이언트 구현 (GeminiClient)
- AI 기능 테스트용 엔드포인트 추가 (`POST /api/ai/chat`)
- HTTP 요청을 위한 Ktor HttpClient 설정
- API 인증을 위해 x-goog-api-key 헤더 사용
- 디버그 로그에서 민감정보 제거
- 의존성 업데이트: Ktor 3.2.3, Koog agents 0.6.0
- gemini-2.5-flash 모델 사용

[![v0.0.11](https://img.shields.io/badge/v0.0.11-gray)](./docs/CHANGELOG.md#v0011) `2025.11.30 14:25`
- 로컬 개발 시 MongoDB Atlas 연결을 위해 local 프로파일 기본 활성화
- prod 배포 시 환경변수로 프로파일 오버라이드


[전체 변경 내역 →](./docs/CHANGELOG.md)

## Tech Stack

- **Language**: Kotlin 2.2.21
- **Framework**: Spring Boot 4.0.0
- **JDK**: 24
- **Database**: MongoDB Cloud (Atlas)
- **AI**: Koog Agents 0.6.0, Google Gemini API
- **HTTP Client**: Ktor 3.2.3
- **Build Tool**: Gradle (Kotlin DSL)

## Features

- REST API 서비스
- MongoDB 연동
- AI 기능 통합 (Google Gemini API)
  - 음성-텍스트 추출 (예정)
  - Google Calendar 자동 등록/수정 (예정)
- Health Check Endpoint

## Getting Started

### Prerequisites

- JDK 24
- MongoDB Cloud Atlas 계정

### Environment Variables

```bash
# Database
MONGODB_URI=mongodb+srv://<username>:<password>@<cluster>.mongodb.net
MONGODB_DATABASE=stash

# AI
AI_GEMINI_KEY=your_gemini_api_key
```

### Run

```bash
./gradlew bootRun
```

### API Examples

**Health Check**
```bash
curl http://localhost:9090/health
```

**AI Integration Test**
```bash
curl -X POST http://localhost:9090/api/ai/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "안녕하세요!"}'
```

> AI 기능 테스트용 엔드포인트. 향후 음성-텍스트 추출, 캘린더 자동화 등에 활용 예정

## Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/ravit/stash/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── domain/
│   │       └── StashApplication.kt
│   └── resources/
│       └── application.yml
└── test/
```
