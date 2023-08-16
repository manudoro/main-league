package com.ar.mainleague.service

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.Position

interface PlayerService {
    fun inscribePlayer(pos: Position, age: Int, name: String, lastName: String, rating: Double): Player

    fun getPlayer(id : Long): Player

    fun getAll(): List<Player>

    fun clear()
}