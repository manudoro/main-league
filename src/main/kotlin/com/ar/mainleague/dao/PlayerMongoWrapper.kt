package com.ar.mainleague.dao

import com.ar.mainleague.modelo.PlayerMongo
import com.ar.mainleague.modelo.PlayerSearchFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class PlayerMongoWrapper {

    @Autowired private lateinit var playerDAO : PlayerMongoDAO
    @Autowired private lateinit var mongoTemplate: MongoTemplate

    fun save(player : PlayerMongo): PlayerMongo{
        return playerDAO.save(player)
    }

    fun findByRelId(id: Long):PlayerMongo{
        return playerDAO.findByRelId(id)
    }

    fun deleteByRelId(id : Long){
        playerDAO.deleteByRelId(id)
    }

    fun deleteAll(){
        playerDAO.deleteAll()
    }

    fun searchPlayers(searchFilter: PlayerSearchFilter): List<PlayerMongo> {
        return mongoTemplate.find(searchFilter.getQuery(), PlayerMongo::class.java)

    }


    fun findAll(): List<PlayerMongo> {
        return playerDAO.findAll()
    }

    fun findAllByIdIn(ids : List<Long>) : List<PlayerMongo> {
        return playerDAO.findAllByIdIn(ids)
    }


}
