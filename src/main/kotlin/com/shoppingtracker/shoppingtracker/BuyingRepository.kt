package com.shasr.springbootkotlinbuyingapp.repository

import com.shoppingtracker.shoppingtracker.MessageResource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BuyingRepository : CrudRepository<MessageResource.Buying, Long>