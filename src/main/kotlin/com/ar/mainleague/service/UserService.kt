package com.ar.mainleague.service

import com.ar.mainleague.modelo.*

interface UserService {



    fun createUser(nickname:String, formacion: Formation): User

    fun getUser(userId : Long): User

    fun getUserBudget(nickname: String): Double

    fun getUserByNickname(nickname : String) : User

    fun getPlayers(nickname: String): List<PlayerOverview>

    fun pickPlayer(nickname: String, playerId : Long)

    fun changeFormation(nickname: String, formation:Formation)

    fun getFormation(nickname: String): Formation

    fun substitutePlayer(nickname: String, playerOutId: Long, playerInId: Long)
}