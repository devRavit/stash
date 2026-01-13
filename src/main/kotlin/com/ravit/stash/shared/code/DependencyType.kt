package com.ravit.stash.shared.code

enum class DependencyType(
    val critical: Boolean,
) {
    MONGODB(critical = true),
    JIRA(critical = false),
    SLACK(critical = false),
}
