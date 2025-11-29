# Changelog

프로젝트 변경 내역 (최신순)

<!-- CHANGELOG_START -->

## v0.0.7
`2025.11.29 22:30`

AWS Secrets Manager 동적 주입 지원

- MongoDB URI를 환경변수에서 Secrets Manager 주입 방식으로 변경
- `spring.mongodb.uri` → `SPRING_MONGODB_URI` 환경변수로 자동 변환
- production 환경에서 deployment-hub를 통한 secret 주입

---

## v0.0.6
`2025.11.29 22:00`

Health 엔드포인트와 Status 모니터링 엔드포인트 분리

- `/health`: AWS 헬스체크용 단순 "OK" 응답
- `/internal/status`: 상세 상태 모니터링 (버전, DB 연결 상태)
- `StatusInternalController` 추가
- `BuildProperties`를 활용한 버전 정보 노출

---

## v0.0.5
`2025.11.29 17:30`

MongoDB Atlas 연동 및 CI/CD 개선

- MongoDB Atlas 클러스터 연결 설정
- local/prod 환경별 프로필 분리
- MongoConfiguration 추가 (connection pool, socket, cluster 설정)
- GitHub Deployments 연동 (배포 상태 표시)
- 배포 성공 시 자동 Release 생성 (v태그)

---

## v0.0.4
`2025.11.27 00:00`

Spring Boot 4.0 호환성 및 배포 개선

- Jackson write-dates-as-timestamps 옵션 제거
- Graceful shutdown 설정 추가 (30초 타임아웃)
- ECR 이미지 태그를 버전 기반으로 변경

---

## v0.0.3
`2025.11.26 23:00`

AWS App Runner CI/CD 파이프라인 구축

- Docker 컨테이너 이미지 빌드 설정
- AWS ECR/App Runner 서비스 생성
- GitHub Actions CI/CD 워크플로우 추가

---

## v0.0.2
`2025.11.26 21:00`

Kotlin 2.2.21 및 JDK 24 마이그레이션

- Kotlin 2.1.0 → 2.2.21 업그레이드
- JDK 25 → 24 다운그레이드 (Kotlin 호환성)
- ktlint 스타일 수정

<!-- CHANGELOG_END -->