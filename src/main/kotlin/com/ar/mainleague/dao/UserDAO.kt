package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserDAO : JpaRepository<User, Long> {

    fun existsByNickname(nickname: String): Boolean

    @Query("SELECT p FROM Player p JOIN p.users u WHERE u.nickname = :nickname")
    fun getPlayersByUser(nickname: String): List<Player>

    fun findByNickname(nickname: String) : User?

    @Query("SELECT u.budget FROM User u WHERE u.nickname = :nickname")
    fun findBudgetByNickname(nickname: String): Double

    @Query("SELECT u.nickname FROM User u")
    fun getNicknames() : List<String>

    @Query("SELECT p.id FROM Player p JOIN p.users u WHERE u.nickname = :nickname")
    fun getPlayersIdByUser(nickname: String): List<Long>

    @Query("SELECT u.nickname FROM User u WHERE u.readyToPlay = true")
    fun getNicknamesReady(): List<String>
}