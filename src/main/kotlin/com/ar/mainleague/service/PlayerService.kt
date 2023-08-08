package com.ar.mainleague.service

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.PlayerOverview
import com.ar.mainleague.modelo.PlayerSearchFilter
import com.ar.mainleague.modelo.Position

interface PlayerService {
    fun inscribePlayer(pos: Position, age: Int, name: String, lastName: String): Player

    fun getPlayer(id : Long): Player

    fun getPlayerStats(id:Long) : PlayerOverview

    fun getAll(): List<PlayerOverview>

    fun researchPlayers(criteria: PlayerSearchFilter): List<PlayerOverview>

    fun clear()
}