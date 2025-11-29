# Stash

개인 포트폴리오 및 실험용 백엔드 API 서비스

## Recent Changes

[![v0.0.7](https://img.shields.io/badge/v0.0.7-purple)](./docs/CHANGELOG.md#v007) `2025.11.29 22:30`
- MongoDB URI를 환경변수에서 Secrets Manager 주입 방식으로 변경
- `spring.mongodb.uri` → `SPRING_MONGODB_URI` 환경변수로 자동 변환
- production 환경에서 deployment-hub를 통한 secret 주입

[![v0.0.6](https://img.shields.io/badge/v0.0.6-gray)](./docs/CHANGELOG.md#v006) `2025.11.29 22:00`
- `/health`: AWS 헬스체크용 단순 "OK" 응답
- `/internal/status`: 상세 상태 모니터링 (버전, DB 연결 상태)
- `StatusInternalController` 추가
- `BuildProperties`를 활용한 버전 정보 노출

[![v0.0.5](https://img.shields.io/badge/v0.0.5-gray)](./docs/CHANGELOG.md#v005) `2025.11.29 17:30`
- MongoDB Atlas 클러스터 연결 설정
- local/prod 환경별 프로필 분리
- MongoConfiguration 추가 (connection pool, socket, cluster 설정)
- GitHub Deployments 연동 (배포 상태 표시)
- 배포 성공 시 자동 Release 생성 (v태그)


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
