package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Round
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.core.aggregation.Aggregation.*

@Repository
class RoundCustomRepositoryImpl @Autowired constructor(private val mongoTemplate: MongoTemplate) : RoundCustomRepository {

    override fun sumScoresByKeysForDocument(roundId: String, ids: List<Long>): Int {
        val aggregation = newAggregation(
            match(Criteria.where("_id").`is`(roundId)),
            project().apply {
                ids.forEach { id -> andInclude("scores.$id") }
            },
            group().sum("\$scores").`as`("totalSum")
        )

        val aggregationResults: AggregationResults<Result> =
            mongoTemplate.aggregate(aggregation, Round::class.java, Result::class.java)

        return aggregationResults.uniqueMappedResult?.totalSum ?: 0
    }

    private data class Result(val totalSum: Int)
}
