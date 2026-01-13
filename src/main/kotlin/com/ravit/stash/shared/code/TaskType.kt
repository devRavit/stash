package com.ravit.stash.shared.code

enum class TaskType(
    val displayName: String,
) {
    FEATURE("신규 기능"),
    IMPROVEMENT("개선"),
    BUG_FIX("버그 수정"),
    REFACTORING("리팩토링"),
    RESEARCH("조사/분석"),
    DOCUMENTATION("문서화"),
}
