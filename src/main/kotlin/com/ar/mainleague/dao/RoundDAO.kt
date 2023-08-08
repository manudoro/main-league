package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Round
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RoundDAO : MongoRepository<Round, String> {
}