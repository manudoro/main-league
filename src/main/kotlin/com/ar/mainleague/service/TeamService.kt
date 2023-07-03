package com.ar.mainleague.service

import com.ar.mainleague.modelo.Player

interface TeamService {

    fun researchPlayers(criteria: Map<String, Any>, size: Int): List<Player>
}