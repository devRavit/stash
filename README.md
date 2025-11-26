# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

> [![v0.0.4](https://img.shields.io/badge/v0.0.4-purple)](./docs/CHANGELOG.md#v0.0.4) `2025.11.27 00:00`
> - Jackson write-dates-as-timestamps 옵션 제거
> - Graceful shutdown 설정 추가 (30초 타임아웃)
> - ECR 이미지 태그를 버전 기반으로 변경

> [![v0.0.3](https://img.shields.io/badge/v0.0.3-gray)](./docs/CHANGELOG.md#v0.0.3) `2025.11.26 23:00`
> - Docker 컨테이너 이미지 빌드 설정
> - AWS ECR/App Runner 서비스 생성
> - GitHub Actions CI/CD 워크플로우 추가

> [![v0.0.2](https://img.shields.io/badge/v0.0.2-gray)](./docs/CHANGELOG.md#v0.0.2) `2025.11.26 21:00`
> - Kotlin 2.1.0 → 2.2.21 업그레이드
> - JDK 25 → 24 다운그레이드 (Kotlin 호환성)
> - ktlint 스타일 수정

[전체 변경 내역 →](./docs/CHANGELOG.md)

## Tech Stack

- **Language**: Kotlin 2.2.21
- **Framework**: Spring Boot 4.0.0
- **JDK**: 24
- **Database**: MongoDB Cloud (Atlas)
- **Build Tool**: Gradle (Kotlin DSL)

## Features

- REST API 서비스
- MongoDB 연동
- Health Check Endpoint

## Getting Started

### Prerequisites

- JDK 24
- MongoDB Cloud Atlas 계정

### Environment Variables

```bash
MONGODB_URI=mongodb+srv://<username>:<password>@<cluster>.mongodb.net
MONGODB_DATABASE=stash
```

### Run

```bash
./gradlew bootRun
```

### Health Check

```bash
curl http://localhost:9090/health
```

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
