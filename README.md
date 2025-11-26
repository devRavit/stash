# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

> [![v0.0.4](https://img.shields.io/badge/v0.0.4-purple)](./docs/CHANGELOG.md#v004) `2025.11.27 00:00`
> - Jackson write-dates-as-timestamps 옵션 제거

> [![- Graceful shutdown 설정 추가 (30초 타임아웃)](https://img.shields.io/badge/- Graceful shutdown 설정 추가 (30초 타임아웃)-gray)](./docs/CHANGELOG.md#- Graceful shutdown 설정 추가 (30초 타임아웃)) ``

> [![- ECR 이미지 태그를 버전 기반으로 변경](https://img.shields.io/badge/- ECR 이미지 태그를 버전 기반으로 변경-gray)](./docs/CHANGELOG.md#- ECR 이미지 태그를 버전 기반으로 변경) ``

> [![](https://img.shields.io/badge/-gray)](./docs/CHANGELOG.md#) ``

> [![v0.0.3](https://img.shields.io/badge/v0.0.3-gray)](./docs/CHANGELOG.md#v003) `2025.11.26 23:00`
> - Docker 컨테이너 이미지 빌드 설정

> [![- AWS ECR/App Runner 서비스 생성](https://img.shields.io/badge/- AWS ECR/App Runner 서비스 생성-gray)](./docs/CHANGELOG.md#- AWS ECR/App Runner 서비스 생성) ``

> [![- GitHub Actions CI/CD 워크플로우 추가](https://img.shields.io/badge/- GitHub Actions CI/CD 워크플로우 추가-gray)](./docs/CHANGELOG.md#- GitHub Actions CI/CD 워크플로우 추가) ``

> [![](https://img.shields.io/badge/-gray)](./docs/CHANGELOG.md#) ``

> [![v0.0.2](https://img.shields.io/badge/v0.0.2-gray)](./docs/CHANGELOG.md#v002) `2025.11.26 21:00`
> - Kotlin 2.1.0 → 2.2.21 업그레이드

> [![- JDK 25 → 24 다운그레이드 (Kotlin 호환성)](https://img.shields.io/badge/- JDK 25 → 24 다운그레이드 (Kotlin 호환성)-gray)](./docs/CHANGELOG.md#- JDK 25 → 24 다운그레이드 (Kotlin 호환성)) ``

> [![- ktlint 스타일 수정](https://img.shields.io/badge/- ktlint 스타일 수정-gray)](./docs/CHANGELOG.md#- ktlint 스타일 수정) ``

> [![](https://img.shields.io/badge/-gray)](./docs/CHANGELOG.md#) ``

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
