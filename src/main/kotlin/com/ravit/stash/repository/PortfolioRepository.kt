package com.ravit.stash.repository

import com.ravit.stash.domain.CompanyType
import com.ravit.stash.domain.Portfolio
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PortfolioRepository : MongoRepository<Portfolio, ObjectId> {
    fun findByCompany(company: CompanyType): List<Portfolio>

    fun findByTechStackContaining(techStack: String): List<Portfolio>
}
