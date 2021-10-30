package com.shoppingtracker.shoppingtracker

import com.shasr.springbootkotlinbuyingapp.repository.BuyingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.*

@SpringBootApplication
class ShoppingtrackerApplication


fun main(args: Array<String>) {
	runApplication<ShoppingtrackerApplication>(*args)
}

@RestController
class MessageResource (val buyingRepository:BuyingRepository) {

	@GetMapping
	fun index(): List<Buying> = listOf(
		Buying(1,"example", 3.50),
		Buying(2,"another example", 30.99),
	)


	@GetMapping("/buyings")
	fun fetchBuyings(): ResponseEntity<List<Buying>> {
		val buyings = buyingRepository.findAll()
		if (buyings.isEmpty()) {
			return ResponseEntity<List<Buying>>(HttpStatus.NO_CONTENT)
		}
		return ResponseEntity<List<Buying>>(buyings, HttpStatus.OK)
	}


	@Entity
	@Table(name = "BUYING")
	data class Buying(@Id @GeneratedValue(strategy = GenerationType.AUTO) val buyingId : Long, val description: String?, val price: Double)

}
