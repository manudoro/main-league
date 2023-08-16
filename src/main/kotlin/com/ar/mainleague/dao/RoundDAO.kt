package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Round
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RoundDAO : MongoRepository<Round, String> {
    fun findByRound(roundNo: Long): Round?




}


