package com.ar.mainleague.dao

import com.ar.mainleague.modelo.PlayerOverview
import com.ar.mainleague.modelo.PlayerSearchFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class PlayerMongoWrapper {

    @Autowired private lateinit var playerDAO : PlayerMongoDAO
    @Autowired private lateinit var mongoTemplate: MongoTemplate

    fun save(player : PlayerOverview): PlayerOverview{
        return playerDAO.save(player)
    }

    fun findByRelId(id: Long):PlayerOverview{
        return playerDAO.findByRelId(id)
    }

    fun deleteByRelId(id : Long){
        playerDAO.deleteByRelId(id)
    }

    fun deleteAll(){
        playerDAO.deleteAll()
    }

    fun searchPlayers(searchFilter: PlayerSearchFilter): List<PlayerOverview> {
        return mongoTemplate.find(searchFilter.getQuery(), PlayerOverview::class.java)

    }


    fun findAll(): List<PlayerOverview> {
        return playerDAO.findAll()
    }

    fun findAllByIdIn(ids : List<Long>) : List<PlayerOverview> {
        return playerDAO.findAllByRelIdIn(ids)
    }

    fun findRateByRelId(id : Long) : PlayerOverview{
        return playerDAO.findRateByRelId(id)
    }


}
