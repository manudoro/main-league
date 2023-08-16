package com.ar.mainleague.modelo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("roundTable")
class RoundTable(val round: Long, val scores : Map<String, Int?>) {

    @Id
    var id : String? = null

    var roundWinner : Pair<String, Int?>? = null

    init {
        roundWinner = scores.entries.maxByOrNull { it.value!! }?.toPair()
    }
}