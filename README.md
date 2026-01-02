# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

[![v0.1.0](https://img.shields.io/badge/v0.1.0-purple)](./docs/CHANGELOG.md#v010) `2026.01.02 21:30`
- Koog agents 프레임워크 기반 AI 기능 통합
- Google Gemini API 연동 (음성-텍스트 추출, 캘린더 자동화 등 준비)
- AI 기능 테스트용 엔드포인트 구현 (`POST /api/ai/chat`)
- gemini-2.5-flash 모델 사용

[![v0.0.11](https://img.shields.io/badge/v0.0.11-gray)](./docs/CHANGELOG.md#v0011) `2025.11.30 14:25`
- 로컬 개발 시 MongoDB Atlas 연결을 위해 local 프로파일 기본 활성화
- prod 배포 시 환경변수로 프로파일 오버라이드

[![v0.0.10](https://img.shields.io/badge/v0.0.10-gray)](./docs/CHANGELOG.md#v0010) `2025.11.30 04:00`
- `DependencyHealthIndicator` 인터페이스 도입으로 확장 가능한 health check 구조
- MongoDB replica set 노드 상태 모니터링 (PRIMARY/SECONDARY/ARBITER)
- UP/DEGRADED/DOWN 3단계 상태 체계 도입


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
