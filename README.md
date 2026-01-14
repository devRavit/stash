# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

[![v0.2.1](https://img.shields.io/badge/v0.2.1-purple)](./docs/CHANGELOG.md#v021) `2026.01.15 00:30`
- Controller를 externals/internals 패키지로 분리
- API path 변경: /api/* → /externals/*, /internal/* → /internals/*
- request/response 패턴 적용 (External 접미사)
- 서비스 레이어 Command 패턴 도입 (ai/model/command)
- 파일당 하나의 클래스/인터페이스 규칙 적용

[![v0.2.0](https://img.shields.io/badge/v0.2.0-gray)](./docs/CHANGELOG.md#v020) `2026.01.14 17:00`
- Project, Task 도메인 추가 (이력서/포트폴리오 작업 이력 관리)
- Gemini 기반 Task 키워드 자동 추출 기능 추가
- 파일당 하나의 클래스/인터페이스 규칙 적용
- shared/code 패키지에 enum 분리 (CompanyType, TaskType, HealthStatusType 등)
- ai 패키지 분리 (stash.ai.*)
- Health 관련 클래스 파일 분리
- Portfolio → Project로 리네이밍 및 구조 개선

[![v0.1.1](https://img.shields.io/badge/v0.1.1-gray)](./docs/CHANGELOG.md#v011) `2026.01.03 02:00`
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
curl http://localhost:9090/externals/health
```

**AI Integration Test**
```bash
curl -X POST http://localhost:9090/externals/ai/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "안녕하세요!"}'
```

> AI 기능 테스트용 엔드포인트. 향후 음성-텍스트 추출, 캘린더 자동화 등에 활용 예정

## Project Structure

```
src/main/kotlin/com/ravit/stash/
├── StashApplication.kt
├── ai/                              # AI 관련 기능
│   ├── agent/                       # Koog 에이전트
│   ├── executor/                    # 프롬프트 실행기
│   ├── model/command/               # 서비스 레이어 Command
│   └── service/                     # AI 서비스
├── configuration/                   # Spring 설정
├── controller/                      # REST API 엔드포인트
│   ├── externals/                   # 외부 API (/externals/*)
│   │   ├── ai/                      # /externals/ai/chat
│   │   ├── calendar/                # /externals/calendar/events
│   │   ├── health/                  # /externals/health
│   │   ├── project/                 # /externals/projects
│   │   └── task/                    # /externals/tasks
│   ├── internals/                   # 내부 API (/internals/*)
│   │   └── status/                  # /internals/status
│   └── GlobalExceptionHandler.kt
├── domain/                          # 도메인 레이어
│   ├── calendar/                    # 캘린더 도메인
│   │   ├── client/
│   │   ├── dto/
│   │   ├── exception/
│   │   └── service/
│   ├── project/                     # 프로젝트 도메인
│   │   ├── document/
│   │   ├── repository/
│   │   └── service/
│   └── task/                        # 태스크 도메인
│       ├── document/
│       ├── repository/
│       └── service/
├── health/                          # 헬스체크 인디케이터
├── infrastructure/                  # 외부 시스템 연동
│   ├── extension/
│   ├── gemini/                      # Google Gemini API
│   └── google/calendar/             # Google Calendar API
├── shared/                          # 공유 코드
│   └── code/                        # Enum 타입 정의
└── utility/                         # 유틸리티
```
