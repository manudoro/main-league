package com.ar.mainleague.modelo

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document
class Round {

    @Id
    lateinit var id : String

    var scores : MutableMap<Long, Int> = mutableMapOf()



    fun ratePlayers(players: List<PlayerOverview>) {
        players.forEach { p -> scores.put(p.relId!!, (0..20).random()) }

    }

}