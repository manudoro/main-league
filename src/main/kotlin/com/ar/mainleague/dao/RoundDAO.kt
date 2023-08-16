package com.ar.mainleague.dao

import com.ar.mainleague.modelo.PlayerScore
import com.ar.mainleague.modelo.Round
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RoundDAO : MongoRepository<Round, String> {
    fun findByRound(roundNo: Long): Round?

    @Aggregation(pipeline = [
        "{ \$match: { round: ?0 } }",
        "{ \$project: { scores: { \$objectToArray: '\$scores' } } }",
        "{ \$unwind: '\$scores' }",
        "{ \$project: { playerId: '\$scores.k', score: '\$scores.v', _id: 0 } }",
        "{ \$sort: { score: -1 } }",
        "{ \$limit: 10 }"
        ]
    )
    fun getTopScores(roundNo : Long): List<PlayerScore>


}


