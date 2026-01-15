package com.ravit.stash.controller.externals.session.request

data class SessionExternalRequest(
    val clientId: String,
    val deviceHint: String?,
)
