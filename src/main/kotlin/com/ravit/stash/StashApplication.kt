package com.ravit.stash

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StashApplication

fun main(args: Array<String>) {
    runApplication<StashApplication>(*args)
}
