# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

| Version | Date | Description |
|:-------:|:----:|:------------|
| ![v0.0.4](https://img.shields.io/badge/v0.0.4-purple) | 2025-11-27 | Spring Boot 4.0 호환성 및 배포 개선 |
| ![v0.0.3](https://img.shields.io/badge/v0.0.3-gray) | 2025-11-26 | AWS App Runner CI/CD 파이프라인 구축 |
| ![v0.0.2](https://img.shields.io/badge/v0.0.2-gray) | 2025-11-26 | Kotlin 2.2.21 및 JDK 24 마이그레이션 |

> 전체 변경 내역은 [CHANGELOG](./docs/CHANGELOG.md)를 참조하세요.

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
