package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerDAO : JpaRepository<Player, Long>{
}