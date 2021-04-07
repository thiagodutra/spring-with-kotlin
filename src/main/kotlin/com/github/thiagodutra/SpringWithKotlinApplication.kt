package com.github.thiagodutra

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWithKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringWithKotlinApplication>(*args)
}