# AWS 리전 마이그레이션 가이드

## 현재 상태
- **현재 리전**: ap-northeast-1 (도쿄)
- **목표 리전**: ap-northeast-2 (서울) - App Runner 지원 시

## 왜 도쿄 리전인가?
- App Runner가 서울 리전(ap-northeast-2)을 지원하지 않음 (2025-11 기준)
- 가장 가까운 지원 리전이 도쿄(ap-northeast-1)

## 서울 리전 지원 확인 방법
```bash
aws apprunner list-services --region ap-northeast-2
```
에러 없이 응답이 오면 지원 시작된 것.

또는 [AWS App Runner 지원 리전](https://docs.aws.amazon.com/general/latest/gr/apprunner.html) 확인

## 마이그레이션 절차

### 1. 새 리전에 ECR 생성
```bash
aws ecr create-repository --repository-name stash --region ap-northeast-2
```

### 2. 새 리전에 IAM 역할 생성 (이미 글로벌이라 필요 없을 수 있음)
기존 역할 `stash-apprunner-ecr-role`이 글로벌이라 재사용 가능

### 3. 이미지 새 리전으로 복사
```bash
# 기존 이미지 pull
docker pull 793586947020.dkr.ecr.ap-northeast-1.amazonaws.com/stash:latest

# 새 리전 태그
docker tag 793586947020.dkr.ecr.ap-northeast-1.amazonaws.com/stash:latest \
  793586947020.dkr.ecr.ap-northeast-2.amazonaws.com/stash:latest

# 새 리전 로그인 & push
aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 793586947020.dkr.ecr.ap-northeast-2.amazonaws.com
docker push 793586947020.dkr.ecr.ap-northeast-2.amazonaws.com/stash:latest
```

### 4. 새 리전에 App Runner 생성
```bash
aws apprunner create-service \
  --service-name stash \
  --source-configuration '{
    "AuthenticationConfiguration": {
      "AccessRoleArn": "arn:aws:iam::793586947020:role/stash-apprunner-ecr-role"
    },
    "ImageRepository": {
      "ImageIdentifier": "793586947020.dkr.ecr.ap-northeast-2.amazonaws.com/stash:latest",
      "ImageRepositoryType": "ECR",
      "ImageConfiguration": {
        "Port": "9090"
      }
    }
  }' \
  --instance-configuration '{"Cpu": "0.25 vCPU", "Memory": "0.5 GB"}' \
  --region ap-northeast-2
```

### 5. GitHub Secrets 업데이트
- `AWS_REGION`: `ap-northeast-1` → `ap-northeast-2`

### 6. CI/CD 워크플로우 ECR 주소 변경
`.github/workflows/deploy.yml`에서:
- `ap-northeast-1` → `ap-northeast-2`

### 7. 새 서비스 정상 동작 확인 후 기존 리소스 삭제
```bash
# 기존 App Runner 삭제
aws apprunner delete-service --service-arn <기존-서비스-arn> --region ap-northeast-1

# 기존 ECR 삭제 (이미지 먼저 삭제 필요)
aws ecr delete-repository --repository-name stash --region ap-northeast-1 --force
```

## 주의사항
- DNS/도메인 연결했다면 새 App Runner URL로 변경 필요
- MongoDB Cloud는 리전 무관 (별도 조치 불필요)
