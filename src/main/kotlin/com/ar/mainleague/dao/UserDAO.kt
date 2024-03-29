package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserDAO : JpaRepository<User, Long> {

    fun existsByNickname(nickname: String): Boolean

    @Query("SELECT p.id FROM Player p JOIN p.users u WHERE u.nickname = :nickname")
    fun getPlayersByUser(nickname: String): List<Long>

    fun findByNickname(nickname: String) : User?

    @Query("SELECT u.budget FROM User u WHERE u.nickname = :nickname")
    fun findBudgetByNickname(nickname: String): Double


}