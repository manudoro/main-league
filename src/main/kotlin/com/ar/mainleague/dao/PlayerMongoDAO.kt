package com.ar.mainleague.dao

import com.ar.mainleague.modelo.PlayerMongo
import org.springframework.data.mongodb.core.aggregation.MatchOperation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerMongoDAO : MongoRepository<PlayerMongo, String> {

    fun save(player : PlayerMongo):PlayerMongo

    fun deleteByRelId(id : Long)

    fun findByRelId(id:Long): PlayerMongo



}