package com.ar.mainleague.dao

import com.ar.mainleague.modelo.PlayerMongo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.AggregationOperation
import org.springframework.data.mongodb.core.aggregation.MatchOperation
import org.springframework.data.mongodb.core.aggregation.TypedAggregation
import org.springframework.data.mongodb.core.query.Criteria
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

    fun searchPlayers(criteriaMap: Map<String, Any>, limit: Long): List<PlayerMongo> {
        val matchOperations = mutableListOf<MatchOperation>()

        criteriaMap.forEach { (attribute, value) ->
            val matchOperation = Aggregation.match(Criteria.where(attribute).`is`(value))
            matchOperations.add(matchOperation)
        }

        val limitOperation= Aggregation.limit(limit)
        val aggregationOperations: List<AggregationOperation> = matchOperations + limitOperation
        val aggregation = TypedAggregation.newAggregation(PlayerMongo::class.java, aggregationOperations)

        return mongoTemplate.aggregate(aggregation, PlayerMongo::class.java).mappedResults

    }


}
