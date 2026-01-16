# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

[![v0.2.12](https://img.shields.io/badge/v0.2.12-purple)](./docs/CHANGELOG.md#v0212) `2026.01.17 03:10`
- production profile 설정 간소화
- App Runner 서비스 재배포 트리거

[![v0.2.11](https://img.shields.io/badge/v0.2.11-gray)](./docs/CHANGELOG.md#v0211) `2026.01.17 02:15`
- production 로깅 설정 복구 (com.ravit.stash: INFO)
- /externals/health endpoint 포함된 이미지 배포

[![v0.2.10](https://img.shields.io/badge/v0.2.10-gray)](./docs/CHANGELOG.md#v0210) `2026.01.17 17:30`
- application.yml로 spring.config.import 설정 이동
- profile-specific 파일의 config import는 Spring Boot 4.0에서 처리되지 않음
- application-production.yml에서 해당 설정 제거


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
