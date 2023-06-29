package com.ar.mainleague.dao

import com.ar.mainleague.modelo.PlayerMongo
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerMongoDAO : MongoRepository<PlayerMongo, String> {
}