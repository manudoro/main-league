package com.ar.mainleague.service

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.service.impl.PlayerSearchFilter

interface TeamService {

    fun researchPlayers(criteria: PlayerSearchFilter): List<Player>
}