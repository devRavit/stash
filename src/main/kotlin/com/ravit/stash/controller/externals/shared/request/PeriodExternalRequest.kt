package com.ravit.stash.controller.externals.shared.request

import com.ravit.stash.shared.document.Period
import java.time.LocalDate

data class PeriodExternalRequest(
    val startedAt: LocalDate?,
    val endedAt: LocalDate?,
) {
    fun toPeriod(): Period =
        Period(
            startedAt = startedAt,
            endedAt = endedAt,
        )
}
