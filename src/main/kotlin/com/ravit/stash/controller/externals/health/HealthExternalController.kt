package com.ravit.stash.controller.externals.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/externals/health")
class HealthExternalController {
    @GetMapping
    fun health(): String = "OK"
}
