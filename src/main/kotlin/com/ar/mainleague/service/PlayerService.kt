package com.ar.mainleague.service

import com.ar.mainleague.modelo.Player

interface PlayerService {
    fun inscribePlayer(pos: String, age: Int, name: String, lastName: String): Player

    fun getPlayer(id : Long): Player

    fun clear()
}