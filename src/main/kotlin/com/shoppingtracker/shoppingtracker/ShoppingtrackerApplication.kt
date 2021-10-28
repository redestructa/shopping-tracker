package com.shoppingtracker.shoppingtracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShoppingtrackerApplication

fun main(args: Array<String>) {
	runApplication<ShoppingtrackerApplication>(*args)
}

@RestController
class MessageResource {
	@GetMapping
	fun index(): List<Message> = listOf(
		Buying("example", 3.50),
		Buying("another example", 30.99),
	)
}

data class Buying(val description: String?, val price: Double)