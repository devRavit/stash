#!/bin/bash

BASE_URL="http://localhost:9090"

extract_id() {
  echo "$1" | grep -o '"id":"[^"]*"' | head -1 | sed 's/"id":"//;s/"//'
}

echo "=== Deleting all existing data ==="
curl -s -X DELETE "$BASE_URL/internals/data/all"
echo -e "\n"

echo "=== Creating Projects ==="

# Project 1: 동의서 인식기 API (MINERVA_SOFT)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "MINERVA_SOFT",
    "name": "동의서 인식기 API",
    "summary": "Manager-Agent 구조의 프로세스 기반 병렬 처리 프로그램으로써, 동의서 인식 모듈을 처리",
    "period": { "startedAt": "2020-09-01", "endedAt": "2020-11-30" },
    "techStack": ["ASP.NET Core 3.1", "MSSQL", "Oracle", "RESTful API", "Socket"],
    "achievements": ["프로세스 관리 및 작업 병렬 처리와 비동기 처리", "Manager와 Agent간의 Socket 통신 구현", "API 기초 설계 및 코어 모듈 개발자와의 협업"],
    "role": "Backend"
  }')
PROJECT1_ID=$(extract_id "$RESPONSE")
echo "Created Project 1: $PROJECT1_ID"

# Project 2: 통합 모듈 플랫폼 관리 서비스 (MINERVA_SOFT)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "MINERVA_SOFT",
    "name": "통합 모듈 플랫폼 관리 서비스",
    "summary": "코어 모듈을 패키지화하여 동적으로 확장가능한 형태의 플랫폼 서비스를 관리하는 관리 프로그램",
    "period": { "startedAt": "2020-11-01", "endedAt": "2022-03-31" },
    "techStack": ["Blazor", ".NET Framework", "MSSQL", "Oracle", "Socket", "WebSocket", "SignalR"],
    "achievements": ["ANSI SQL 기반 Database Vendor별 구문 분석 및 SQL 변환 기능 구현", "모니터링 대시보드 및 파일 탐색기 구현", "계정 및 권한 관리 기능 구현"],
    "role": "Backend, Frontend"
  }')
PROJECT2_ID=$(extract_id "$RESPONSE")
echo "Created Project 2: $PROJECT2_ID"

# Project 3: 해외 호텔 운영 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "해외 호텔 운영",
    "summary": "해외 호텔 예약 서비스의 운영 및 기능 개선. 외부 결제 시스템 연동, 인증 체계 개선, 내부 보안 감사 대응",
    "period": { "startedAt": "2022-03-01", "endedAt": "2023-05-31" },
    "techStack": ["C#", "ASP.NET", "MSSQL"],
    "achievements": ["외부 API(네이버페이) 연동으로 서드파티 통합 역량 강화", "ITGC 내부 감사 대응을 통한 보안 컴플라이언스 경험", "레거시 시스템 유지보수 및 운영 이슈 대응"],
    "role": "Backend"
  }')
PROJECT3_ID=$(extract_id "$RESPONSE")
echo "Created Project 3: $PROJECT3_ID"

# Project 4: 신규 패키지 플랫폼 개발 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "신규 패키지 플랫폼 개발",
    "summary": "기존 .NET 기반 해외 패키지 시스템을 Java/Kotlin 기반으로 전환하고, 상품 도메인을 신규 설계하여 확장 가능한 플랫폼 구축",
    "period": { "startedAt": "2023-05-01", "endedAt": "2023-12-31" },
    "techStack": ["Java", "Kotlin", "Spring Boot", "MongoDB", "React"],
    "achievements": ["C# → Java → Kotlin 언어 전환을 통한 플랫폼 마이그레이션 수행", "애자일(스크럼) 방식 도입으로 자발적인 개발 문화 정착에 기여", "상품 도메인 신규 설계 및 API 개발"],
    "role": "Backend"
  }')
PROJECT4_ID=$(extract_id "$RESPONSE")
echo "Created Project 4: $PROJECT4_ID"

# Project 5: 신규패키지 서비스 고도화 및 유지보수 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "신규패키지 서비스 고도화 및 유지보수",
    "summary": "신규패키지 서비스의 안정성 확보 및 기능 개선. 예약/취소 프로세스의 복잡한 비즈니스 로직 구현",
    "period": { "startedAt": "2023-05-01", "endedAt": null },
    "techStack": ["Kotlin", "Spring Boot", "MongoDB", "낙관적 잠금", "카카오 알림톡"],
    "achievements": ["예약 변경 중복 이슈 해결 (낙관적 잠금)", "할인 시스템 전체 결제/환불 플로우 구현", "단위 테스트 200개 이상 작성, 커버리지 85% 이상 유지"],
    "role": "Backend"
  }')
PROJECT5_ID=$(extract_id "$RESPONSE")
echo "Created Project 5: $PROJECT5_ID"

# Project 6: 패키지 예약 시스템 자동화 및 플랫폼 오픈 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "패키지 예약 시스템 자동화 및 플랫폼 오픈",
    "summary": "패키지 예약 시스템의 운영 자동화를 목표로, 주요 프로세스를 자동화하고 이벤트 기반 아키텍처로 전환. 2024.05 인터파크트리플 패키지 플랫폼 최초 오픈",
    "period": { "startedAt": "2024-01-01", "endedAt": "2024-05-31" },
    "techStack": ["Kotlin", "Spring Boot", "MongoDB", "AWS SQS", "Kinesis", "Kafka", "Redis"],
    "achievements": ["이벤트 수신 로직 서버 분리로 시스템 의존성 감소", "메시지 큐 선택 기준 수립", "2024.05 인터파크트리플 패키지 플랫폼 정식 오픈", "평가: A"],
    "role": "Backend"
  }')
PROJECT6_ID=$(extract_id "$RESPONSE")
echo "Created Project 6: $PROJECT6_ID"

# Project 7: 모두투어 상품 연동 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "모두투어 상품 연동",
    "summary": "모두투어 완제품 상품을 패키지 플랫폼에 연동하여 상품 라인업 확대. 외부 업체 API 연동 및 상품 데이터 동기화 시스템 구축",
    "period": { "startedAt": "2024-06-01", "endedAt": "2024-09-30" },
    "techStack": ["Kotlin", "Spring Boot", "MongoDB", "모두투어 API"],
    "achievements": ["외부 API 연동 안정화 (최대 3회 재시도)", "대중소 코드 미매칭 시 Slack 알림 조기 발견 체계 구축", "지역 정보 자동 주입으로 검색 노출 문제 해결"],
    "role": "Backend"
  }')
PROJECT7_ID=$(extract_id "$RESPONSE")
echo "Created Project 7: $PROJECT7_ID"

# Project 8: 투어 빌링 결제 시스템 전환 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "투어 빌링 결제 시스템 전환",
    "summary": "트리플 빌링 시스템에서 투어 빌링 시스템으로의 전환 프로젝트. 결제 수단별 금액 안분 로직 설계 및 구현",
    "period": { "startedAt": "2025-03-01", "endedAt": "2025-05-31" },
    "techStack": ["Kotlin", "Spring Boot", "MongoDB", "Strategy Pattern", "Factory Pattern"],
    "achievements": ["100개 이상의 환불 케이스 도출", "Strategy 패턴으로 결제 수단별 환불 전략 분리", "피처 플래그로 단계적 전환으로 안정적 배포"],
    "role": "Backend"
  }')
PROJECT8_ID=$(extract_id "$RESPONSE")
echo "Created Project 8: $PROJECT8_ID"

# Project 9: 인솔자 예약 및 여행 그룹 시스템 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "인솔자 예약 및 여행 그룹 시스템",
    "summary": "인솔자/파트너 관리 시스템과 여행 그룹 관리 시스템의 전체 설계 및 구현",
    "period": { "startedAt": "2025-05-01", "endedAt": "2025-08-31" },
    "techStack": ["Kotlin", "Spring Boot", "MongoDB", "JWT", "Apache POI"],
    "achievements": ["여행 그룹 도메인 처음부터 설계", "패신저 리스트 동적 생성으로 데이터 정합성 유지", "기획팀/프론트엔드팀/운영팀 교육 진행"],
    "role": "Backend"
  }')
PROJECT9_ID=$(extract_id "$RESPONSE")
echo "Created Project 9: $PROJECT9_ID"

# Project 10: NOL 채널 내재화 (NOL_UNIVERSE)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "NOL_UNIVERSE",
    "name": "NOL 채널 내재화 (임베딩)",
    "summary": "NOL 채널 통합을 위한 백엔드 시스템 설계 및 구현. NOL 전용 결제 수단, 알림톡, 마이페이지 기능 추가",
    "period": { "startedAt": "2025-08-01", "endedAt": "2025-11-30" },
    "techStack": ["Kotlin", "Spring Boot", "MongoDB", "Redis", "NOL API", "브레이즈", "카카오 알림톡"],
    "achievements": ["TPS 20만 대응을 위한 시스템 설계", "Redis 캐싱 전략 수립 (Look-Aside 패턴)", "채널 분리 설계로 기존 코드 변경 최소화"],
    "role": "Backend"
  }')
PROJECT10_ID=$(extract_id "$RESPONSE")
echo "Created Project 10: $PROJECT10_ID"

# Project 11: 10X-THON 해커톤 (PERSONAL)
RESPONSE=$(curl -s -X POST "$BASE_URL/externals/projects" \
  -H "Content-Type: application/json" \
  -d '{
    "company": "PERSONAL",
    "name": "10X-THON 해커톤 - Memento",
    "summary": "AI가 여행 사진을 분석하고 세상에 단 하나뿐인 예술 작품으로 변환하는 기념품 생성 서비스",
    "period": { "startedAt": "2025-11-01", "endedAt": "2025-11-30" },
    "techStack": ["Kotlin", "Spring Boot 3.5", "Next.js", "React", "TypeScript", "Google Gemini AI", "AWS Bedrock Nova Canvas", "AWS S3"],
    "achievements": ["Spec 기반 개발 방법론 적용", "백엔드/프론트엔드 전체 설계 및 구현", "74팀 중 13위 (상위 17%)"],
    "role": "Full-Stack"
  }')
PROJECT11_ID=$(extract_id "$RESPONSE")
echo "Created Project 11: $PROJECT11_ID"

echo -e "\n=== Creating Tasks ==="

# Tasks for Project 1 (동의서 인식기 API)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT1_ID\", \"type\": \"FEATURE\", \"title\": \"Manager 개발\", \"description\": \"Agent 관리 및 WebAPI 인터페이스 개발\"}" > /dev/null
echo "Created Task: Manager 개발"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT1_ID\", \"type\": \"FEATURE\", \"title\": \"Agent 개발\", \"description\": \"유휴기간이 긴 Agent 우선 작업 할당, 동의서 인식\"}" > /dev/null
echo "Created Task: Agent 개발"

# Tasks for Project 2 (통합 모듈 플랫폼 관리 서비스)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT2_ID\", \"type\": \"FEATURE\", \"title\": \"SQL 구문 분석기 개발\", \"description\": \"ANSI SQL을 기반으로 Database Vendor별로 지원 가능한 형태의 구문 분석 및 SQL 변환 기능 구현\"}" > /dev/null
echo "Created Task: SQL 구문 분석기 개발"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT2_ID\", \"type\": \"FEATURE\", \"title\": \"서버 모니터링 대시보드 개발\", \"description\": \"플랫폼 서비스와 Socket을 통한 네트워크 통신 및 모니터링 UI 개발\"}" > /dev/null
echo "Created Task: 서버 모니터링 대시보드 개발"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT2_ID\", \"type\": \"FEATURE\", \"title\": \"서버 사이드 파일 탐색기 구현\", \"description\": \"Linux를 위한 파일 탐색 기능 구현\"}" > /dev/null
echo "Created Task: 서버 사이드 파일 탐색기 구현"

# Tasks for Project 3 (해외 호텔 운영)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT3_ID\", \"type\": \"FEATURE\", \"title\": \"네이버페이 포인트 적립 연동\", \"description\": \"외부 API(네이버페이) 연동으로 서드파티 통합\"}" > /dev/null
echo "Created Task: 네이버페이 포인트 적립 연동"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT3_ID\", \"type\": \"FEATURE\", \"title\": \"PLCC 관련 기능 개발\", \"description\": \"마이페이지 상업자표시신용카드 관련 기능 개발\"}" > /dev/null
echo "Created Task: PLCC 관련 기능 개발"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT3_ID\", \"type\": \"IMPROVEMENT\", \"title\": \"ITGC 내부 감사 대응\", \"description\": \"호텔인, 구하우징 보안 컴플라이언스 대응\"}" > /dev/null
echo "Created Task: ITGC 내부 감사 대응"

# Tasks for Project 4 (신규 패키지 플랫폼 개발)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT4_ID\", \"type\": \"FEATURE\", \"title\": \"투어아이템/세트아이템 도메인 설계 및 API 개발\", \"description\": \"상품 도메인 신규 설계 및 API 개발\"}" > /dev/null
echo "Created Task: 투어아이템/세트아이템 도메인 설계"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT4_ID\", \"type\": \"FEATURE\", \"title\": \"Admin 관리 화면 개발\", \"description\": \"React 기반 Admin 관리 화면 개발\"}" > /dev/null
echo "Created Task: Admin 관리 화면 개발"

# Tasks for Project 5 (신규패키지 서비스 고도화)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT5_ID\", \"type\": \"BUG_FIX\", \"title\": \"예약 변경 중복 문제 해결\", \"description\": \"트랜잭션 로그 2주간 분석하여 동시성 문제 파악 후 낙관적 잠금 적용\"}" > /dev/null
echo "Created Task: 예약 변경 중복 문제 해결"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT5_ID\", \"type\": \"FEATURE\", \"title\": \"추가 할인 시스템 전체 구축\", \"description\": \"결제/환불/알림톡 연동 포함한 할인 시스템 구현\"}" > /dev/null
echo "Created Task: 추가 할인 시스템 전체 구축"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT5_ID\", \"type\": \"IMPROVEMENT\", \"title\": \"알림톡 발송 이력 관리 체계 구축\", \"description\": \"템플릿 enum 관리 체계 및 Strategy 패턴 적용\"}" > /dev/null
echo "Created Task: 알림톡 발송 이력 관리 체계 구축"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT5_ID\", \"type\": \"IMPROVEMENT\", \"title\": \"재고 관리 시스템 강화\", \"description\": \"이중삼중 검증 체계 구축\"}" > /dev/null
echo "Created Task: 재고 관리 시스템 강화"

# Tasks for Project 6 (패키지 예약 시스템 자동화)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT6_ID\", \"type\": \"REFACTORING\", \"title\": \"잔금 TL/대기예약처리 배치 리팩토링\", \"description\": \"배치 처리 로직 리팩토링 및 테스트\"}" > /dev/null
echo "Created Task: 잔금 TL/대기예약처리 배치 리팩토링"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT6_ID\", \"type\": \"FEATURE\", \"title\": \"이벤트 수신 로직 서버 분리\", \"description\": \"메시지 큐 래핑으로 시스템 의존성 감소\"}" > /dev/null
echo "Created Task: 이벤트 수신 로직 서버 분리"

# Tasks for Project 7 (모두투어 상품 연동)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT7_ID\", \"type\": \"FEATURE\", \"title\": \"모두투어 아이템/ItemGroup CRUD API 개발\", \"description\": \"standalone 아이템 및 ItemGroup API 설계 및 구현\"}" > /dev/null
echo "Created Task: 모두투어 아이템/ItemGroup CRUD API 개발"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT7_ID\", \"type\": \"FEATURE\", \"title\": \"상품 데이터 동기화 배치 개발\", \"description\": \"모두투어 상품 데이터 동기화 및 대중소 코드 매칭\"}" > /dev/null
echo "Created Task: 상품 데이터 동기화 배치 개발"

# Tasks for Project 8 (투어 빌링 결제 시스템 전환)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT8_ID\", \"type\": \"RESEARCH\", \"title\": \"환불 케이스 분류 및 안분 규칙 정의\", \"description\": \"100개 이상의 케이스 도출 및 결제 수단별 환불 우선순위 매트릭스 설계\"}" > /dev/null
echo "Created Task: 환불 케이스 분류 및 안분 규칙 정의"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT8_ID\", \"type\": \"FEATURE\", \"title\": \"취소 안분 서비스 구현\", \"description\": \"Strategy 패턴 활용한 결제 수단별 환불 전략 분리\"}" > /dev/null
echo "Created Task: 취소 안분 서비스 구현"

# Tasks for Project 9 (인솔자 예약 및 여행 그룹 시스템)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT9_ID\", \"type\": \"FEATURE\", \"title\": \"인솔자/파트너 CRUD 및 타입 확장\", \"description\": \"인솔자/가이드/현지업체 파트너 타입 확장\"}" > /dev/null
echo "Created Task: 인솔자/파트너 CRUD 및 타입 확장"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT9_ID\", \"type\": \"FEATURE\", \"title\": \"여행 그룹 CRUD 및 예약 할당 기능\", \"description\": \"예약과의 N:M 관계를 매핑 테이블로 연결하는 구조 설계\"}" > /dev/null
echo "Created Task: 여행 그룹 CRUD 및 예약 할당 기능"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT9_ID\", \"type\": \"FEATURE\", \"title\": \"패신저 리스트 엑셀 다운로드\", \"description\": \"Apache POI SXSSFWorkbook 기반 Streaming 엑셀 다운로드\"}" > /dev/null
echo "Created Task: 패신저 리스트 엑셀 다운로드"

# Tasks for Project 10 (NOL 채널 내재화)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT10_ID\", \"type\": \"FEATURE\", \"title\": \"NOL 포인트/쿠폰 결제 시스템 연동\", \"description\": \"NOL 플랫폼 팀과 포인트/쿠폰 API 스펙 조율 및 구현\"}" > /dev/null
echo "Created Task: NOL 포인트/쿠폰 결제 시스템 연동"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT10_ID\", \"type\": \"FEATURE\", \"title\": \"NOL 베네핏 시스템 구축\", \"description\": \"3종 혜택 시스템 구축\"}" > /dev/null
echo "Created Task: NOL 베네핏 시스템 구축"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT10_ID\", \"type\": \"IMPROVEMENT\", \"title\": \"Redis 캐싱 전략 수립\", \"description\": \"TPS 20만 대응을 위한 Look-Aside 패턴 적용\"}" > /dev/null
echo "Created Task: Redis 캐싱 전략 수립"

# Tasks for Project 11 (해커톤)
curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT11_ID\", \"type\": \"FEATURE\", \"title\": \"AI 처리 파이프라인 설계\", \"description\": \"S3 저장 → Gemini AI 분석 → Nova Canvas 이미지 생성 플로우 구현\"}" > /dev/null
echo "Created Task: AI 처리 파이프라인 설계"

curl -s -X POST "$BASE_URL/externals/tasks?extractKeywords=true" \
  -H "Content-Type: application/json" \
  -d "{\"projectId\": \"$PROJECT11_ID\", \"type\": \"FEATURE\", \"title\": \"백엔드/프론트엔드 전체 구현\", \"description\": \"Spec 기반 개발로 API 명세서, AI 처리 플로우, 서비스 컨셉 문서 작성 후 구현\"}" > /dev/null
echo "Created Task: 백엔드/프론트엔드 전체 구현"

echo -e "\n=== Data seeding completed ==="
echo "Projects created: 11"
echo "Tasks created: 25"
