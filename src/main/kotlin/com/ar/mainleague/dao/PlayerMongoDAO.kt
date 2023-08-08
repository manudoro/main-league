package com.ar.mainleague.dao

import com.ar.mainleague.modelo.PlayerOverview

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PlayerMongoDAO : MongoRepository<PlayerOverview, String> {

    fun deleteByRelId(id : Long)

    fun findByRelId(id:Long): PlayerOverview

    fun findAllByRelIdIn(ids : List<Long>) : List<PlayerOverview>


    @Query("{'relId' : ?0}", fields = "{'rating' : 1}")
    fun findRateByRelId(id : Long) : PlayerOverview


}