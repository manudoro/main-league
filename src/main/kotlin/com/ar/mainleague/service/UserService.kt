package com.ar.mainleague.service

import com.ar.mainleague.modelo.Formacion
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.service.impl.PlayerSearchFilter

interface UserService {

    fun researchPlayers(criteria: PlayerSearchFilter): List<Player>

    fun createUser(nickname:String, formacion: Formacion)

    fun pickPlayer(userId : Long, playerId : Long)

    fun changeFormacion(userId: Long, formacion:Formacion)

    fun chagePlayer(userId: Long, playerOutId: Long, playerInId: Long)

}