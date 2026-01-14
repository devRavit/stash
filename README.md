# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

[![v0.2.3](https://img.shields.io/badge/v0.2.3-purple)](./docs/CHANGELOG.md#v023) `2026.01.15 01:30`
- DataInternalController 추가 (DELETE /internals/data/*)
- Project 응답에 tasks 포함 옵션 추가 (?includeTasks=true)
- seed-data.sh 스크립트 추가 (포트폴리오 데이터 삽입)
- Request DTO nullable 처리 (Jackson 호환)
- KITA_ACADEMY enum 제거

[![v0.2.2](https://img.shields.io/badge/v0.2.2-gray)](./docs/CHANGELOG.md#v022) `2026.01.15 01:00`
- health → actuator 패키지 이동 (Spring Actuator 확장)
- *Properties → property 패키지 분리
- TaskPeriod, ProjectPeriod → shared.document.Period로 통합
- TaskDetails → Task.kt 내부로 이동 (도메인 전용 value object)
- controller/externals/shared 추가 (공용 Request/Response)
- @WithKstZoneTime 어노테이션 추가 (KST 타임존 직렬화)
- @Indexed 추가 (Task.projectId, Task.type, Project.company)
- 객체 변환 패턴 적용 (request.toXxx(), Response.from())
- UUID → ObjectId 전환
- MongoDB auto-index-creation 설정

[![v0.2.1](https://img.shields.io/badge/v0.2.1-gray)](./docs/CHANGELOG.md#v021) `2026.01.15 00:30`
- Controller를 externals/internals 패키지로 분리
- API path 변경: /api/* → /externals/*, /internal/* → /internals/*
- request/response 패턴 적용 (External 접미사)
- 서비스 레이어 Command 패턴 도입 (ai/model/command)
- 파일당 하나의 클래스/인터페이스 규칙 적용


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
├── actuator/           # Spring Actuator 확장 (HealthIndicator 등)
├── ai/                 # AI 기능 (에이전트, 프롬프트 실행기, 서비스)
├── configuration/      # Spring 설정 클래스
├── controller/         # REST API 엔드포인트
│   ├── externals/      # 외부 공개 API (/externals/*)
│   │   └── shared/     # 공용 Request/Response
│   └── internals/      # 내부 관리 API (/internals/*)
├── domain/             # 비즈니스 도메인 (document, repository, service)
├── infrastructure/     # 서드파티 연동 (Gemini, Google Calendar 등)
├── property/           # @ConfigurationProperties 클래스
├── shared/             # 공용 코드
│   ├── annotation/     # 커스텀 어노테이션
│   ├── code/           # Enum 타입
│   ├── document/       # 공용 Document/Value Object
│   └── serializer/     # Jackson Serializer
└── utility/            # 유틸리티
```
