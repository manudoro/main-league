package com.ar.mainleague.service.utils

import com.ar.mainleague.modelo.Round
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation.group
import org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

@Service
class CounterService(private val mongoTemplate: MongoTemplate) {

    fun findMaxValueForField(fieldName: String): Long? {
        val aggregation = newAggregation(
            group().max(fieldName).`as`("maxValue")
        )

        val results = mongoTemplate.aggregate(aggregation, Round::class.java, Map::class.java)

        val resultMap = results.uniqueMappedResult
        return resultMap?.get("maxValue") as? Long
    }
}