package com.ar.mainleague.service

import com.ar.mainleague.modelo.Formation
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.User
import com.ar.mainleague.modelo.PlayerSearchFilter

interface UserService {

    fun researchPlayers(criteria: PlayerSearchFilter): List<Player>

    fun createUser(nickname:String, formacion: Formation): User

    fun getUser(userId : Long): User

    fun getPlayers(userId: Long): List<Player>

    fun pickPlayer(userId : Long, playerId : Long)

    fun changeFormation(userId: Long, formation:Formation)

    fun getFormation(userId: Long): Formation

    fun substitutePlayer(userId: Long, playerOutId: Long, playerInId: Long)
}