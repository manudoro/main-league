package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.Position
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PlayerDAO : JpaRepository<Player, Long>{

    @Query("SELECT COUNT(p) FROM Player p JOIN p.users u WHERE u.id = :userId AND p.position = :position")
    fun countByPositionAndUserId(position: Position, userId: Long): Int

    @Query("SELECT COUNT(p) > 0 FROM Player p JOIN p.users u WHERE u.nickname = :nickname AND p.id = :playerOutId")
    fun existsByIdInUserTeam(playerOutId: Long, nickname: String): Boolean

    @Query("SELECT p.rating FROM Player p where p.id = :id")
    fun findRatingById(id : Long) : Double

    fun findByIdIn(ids : List<Long>) : List<Player>
}