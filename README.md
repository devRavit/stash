# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

[![v0.0.10](https://img.shields.io/badge/v0.0.10-purple)](./docs/CHANGELOG.md#v0010) `2025.11.30 04:00`
- `DependencyHealthIndicator` 인터페이스 도입으로 확장 가능한 health check 구조
- MongoDB replica set 노드 상태 모니터링 (PRIMARY/SECONDARY/ARBITER)
- UP/DEGRADED/DOWN 3단계 상태 체계 도입
- Critical dependency 구분 (DB down = 서비스 DOWN, 비critical = DEGRADED)
- `MongoClient.clusterDescription` 활용하여 Atlas 무료티어 호환

[![v0.0.9](https://img.shields.io/badge/v0.0.9-gray)](./docs/CHANGELOG.md#v009) `2025.11.29 23:00`
- GitHub Actions에서 ECR URL이 secret으로 마스킹되는 문제 해결
- deployment-hub에서 직접 ECR image URI 구성
- `project`, `ECR_REPOSITORY` 하드코딩을 `github.event.repository.name`으로 동적 처리

[![v0.0.8](https://img.shields.io/badge/v0.0.8-gray)](./docs/CHANGELOG.md#v008) `2025.11.29 22:45`
- CI workflow 성공 후 Deploy workflow 실행되도록 변경
- `workflow_run` 이벤트로 workflow 연결
- main push 시 중복 실행 제거


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
