# Changelog

프로젝트 커밋 히스토리 (최신순)

---

## 2025-11-26 (23:00) ![v0.0.3](https://img.shields.io/badge/v0.0.3-purple)
> > ## AWS App Runner CI/CD 파이프라인 구축
> > AWS App Runner를 활용한 배포 자동화 환경을 구축하고, CI 파이프라인에 lint 검사를 추가했습니다.
> > > ### ![Feature](https://img.shields.io/badge/Feature-green) AWS 배포 환경 구축
> > - Docker 컨테이너 이미지 빌드 설정 (Dockerfile)
> > - AWS ECR 레포지토리 생성 (ap-northeast-1)
> > - AWS App Runner 서비스 생성
> > > ### ![Feature](https://img.shields.io/badge/Feature-green) CI/CD 워크플로우
> > - GitHub Actions 배포 워크플로우 (deploy.yml)
> > - CI 워크플로우 with ktlint 검사 (ci.yml)
> > > ### ![Docs](https://img.shields.io/badge/Docs-blue) 문서화
> > - AWS 리전 마이그레이션 가이드 추가

---

## 2025-11-26 (21:00) ![v0.0.2](https://img.shields.io/badge/v0.0.2-purple)
> > ## Kotlin 2.2.21 및 JDK 24 마이그레이션
> > Kotlin 2.2.21 호환성을 위해 JDK 25에서 24로 다운그레이드하고, 관련 설정 및 문서를 정비했습니다.
> > > ### ![Config](https://img.shields.io/badge/Config-gray) Kotlin/JDK 버전 업그레이드
> > - Kotlin 2.1.0 → 2.2.21
> > - JDK 25 → 24 (Kotlin 2.2.21 호환성)
> > > ### ![Bugfix](https://img.shields.io/badge/Bugfix-red) ktlint 스타일 수정
> > - HealthController 코드 스타일 수정
> > > ### ![Docs](https://img.shields.io/badge/Docs-blue) 문서 업데이트
> > - README.md 버전 정보 반영
> > - docs/CHANGELOG.md 추가