package com.ar.mainleague.service

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.PlayerMongo
import com.ar.mainleague.modelo.PlayerSearchFilter
import com.ar.mainleague.modelo.Position

interface PlayerService {
    fun inscribePlayer(pos: Position, age: Int, name: String, lastName: String): Player

    fun getPlayer(id : Long): Player

    fun getPlayerStats(id:Long) : PlayerMongo

    fun getAll(): List<PlayerMongo>

    fun researchPlayers(criteria: PlayerSearchFilter): List<PlayerMongo>

    fun clear()
}