package com.ravit.stash.controller

import com.ravit.stash.domain.CompanyType
import com.ravit.stash.domain.Performance
import com.ravit.stash.domain.PerformanceMetric
import com.ravit.stash.domain.Period
import com.ravit.stash.domain.Portfolio
import com.ravit.stash.domain.ProblemSolving
import com.ravit.stash.domain.SystemDesign
import com.ravit.stash.repository.PortfolioRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/portfolios")
class PortfolioController(
    private val portfolioRepository: PortfolioRepository,
) {
    @GetMapping
    fun getPortfolios(
        @RequestParam(required = false) company: CompanyType?,
    ): List<Portfolio> =
        company?.let { portfolioRepository.findByCompany(it) }
            ?: portfolioRepository.findAll()

    @GetMapping("/{id}")
    fun getPortfolio(
        @PathVariable id: String,
    ): Portfolio? = portfolioRepository.findById(ObjectId(id)).orElse(null)

    @GetMapping("/mock")
    fun getMockPortfolios(): List<Portfolio> = mockPortfolios
}

private val mockPortfolios =
    listOf(
        Portfolio(
            company = CompanyType.NOL_UNIVERSE,
            projectName = "패키지 예약 시스템",
            title = "대용량 트래픽 처리를 위한 예약 시스템 개선",
            summary = "초당 10,000건 이상의 예약 요청을 처리하기 위한 시스템 아키텍처 개선 및 성능 최적화",
            period = Period(startDate = LocalDate.of(2023, 1, 1), endDate = LocalDate.of(2023, 6, 30)),
            techStack = listOf("Kotlin", "Spring Boot", "MongoDB", "Redis", "Kafka"),
            systemDesign =
                SystemDesign(
                    architecture = "MSA 기반 이벤트 드리븐 아키텍처",
                    scalability = "Kubernetes HPA를 활용한 오토스케일링, Redis Cluster 구성",
                    dataFlow = "API Gateway → 예약 서비스 → Kafka → 결제/재고 서비스",
                    diagram = null,
                ),
            performance =
                Performance(
                    metrics =
                        listOf(
                            PerformanceMetric(
                                name = "응답 시간",
                                before = "500ms",
                                after = "50ms",
                                improvement = "90% 개선",
                            ),
                            PerformanceMetric(
                                name = "처리량",
                                before = "1,000 TPS",
                                after = "10,000 TPS",
                                improvement = "10배 증가",
                            ),
                        ),
                    optimizations =
                        listOf(
                            "MongoDB 인덱스 최적화",
                            "Redis 캐싱 전략 도입",
                            "비동기 처리를 통한 응답 시간 단축",
                        ),
                ),
            problemSolving =
                listOf(
                    ProblemSolving(
                        problem = "피크 시간대 DB 병목 현상",
                        analysis = "슬로우 쿼리 분석 결과 N+1 쿼리 및 인덱스 미적용 확인",
                        solution = "복합 인덱스 추가, 쿼리 최적화, Read Replica 도입",
                        result = "DB CPU 사용률 80% → 30%로 감소",
                    ),
                ),
            keyAchievements =
                listOf(
                    "시스템 안정성 99.9% 달성",
                    "비용 30% 절감",
                    "개발팀 생산성 향상을 위한 내부 라이브러리 개발",
                ),
            teamSize = 8,
            role = "백엔드 리드",
        ),
        Portfolio(
            company = CompanyType.MINERVA_SOFT,
            projectName = "Stash",
            title = "개인 포트폴리오 API 서비스",
            summary = "Spring Boot 4.0 + Kotlin 기반 REST API 서비스",
            period = Period(startDate = LocalDate.of(2024, 11, 1), endDate = null),
            techStack = listOf("Kotlin", "Spring Boot 4.0", "MongoDB Atlas", "AWS App Runner"),
            systemDesign =
                SystemDesign(
                    architecture = "모놀리식 아키텍처 (추후 확장 고려)",
                    scalability = "AWS App Runner 기반 자동 스케일링",
                    dataFlow = "Client → App Runner → MongoDB Atlas",
                    diagram = null,
                ),
            performance = null,
            problemSolving = emptyList(),
            keyAchievements =
                listOf(
                    "CI/CD 파이프라인 구축 (GitHub Actions → ECR → App Runner)",
                    "MongoDB Atlas 연동",
                ),
            teamSize = 1,
            role = "풀스택",
        ),
    )
