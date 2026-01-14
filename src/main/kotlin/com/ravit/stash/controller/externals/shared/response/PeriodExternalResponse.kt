package com.ravit.stash.controller.externals.shared.response

import com.ravit.stash.shared.document.Period
import java.time.LocalDate

data class PeriodExternalResponse(
    val startedAt: LocalDate?,
    val endedAt: LocalDate?,
) {
    companion object {
        fun from(period: Period): PeriodExternalResponse =
            PeriodExternalResponse(
                startedAt = period.startedAt,
                endedAt = period.endedAt,
            )
    }
}
