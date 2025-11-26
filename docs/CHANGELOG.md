# Changelog

프로젝트 변경 내역 (최신순)

<!-- CHANGELOG_START -->

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