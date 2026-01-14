package com.ravit.stash

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class StashApplication

fun main(args: Array<String>) {
    runApplication<StashApplication>(*args)
}
