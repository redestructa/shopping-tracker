package com.shoppingtracker.shoppingtracker

import org.springframework.web.bind.annotation.RestController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class ShoppingtrackerApplication

fun main(args: Array<String>) {
	runApplication<ShoppingtrackerApplication>(*args)
}

@RestController
class MessageResource {
	@GetMapping
	fun index(): List<Buying> = listOf(
		Buying("example", 3.50),
		Buying("another example", 30.99),
	)
}

data class Buying(val description: String?, val price: Double)