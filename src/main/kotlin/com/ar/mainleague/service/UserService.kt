package com.ar.mainleague.service

import com.ar.mainleague.modelo.*

interface UserService {



    fun createUser(nickname:String, formacion: Formation): User

    fun getUser(userId : Long): User

    fun getPlayers(userId: Long): List<PlayerMongo>

    fun pickPlayer(userId : Long, playerId : Long)

    fun changeFormation(userId: Long, formation:Formation)

    fun getFormation(userId: Long): Formation

    fun substitutePlayer(userId: Long, playerOutId: Long, playerInId: Long)
}