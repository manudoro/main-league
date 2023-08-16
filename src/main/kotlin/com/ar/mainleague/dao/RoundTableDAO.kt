package com.ar.mainleague.dao

import com.ar.mainleague.modelo.RoundTable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


@Repository
interface RoundTableDAO : MongoRepository<RoundTable, String> {

    fun findRoundTablesByRound(round : Long) : RoundTable?
}