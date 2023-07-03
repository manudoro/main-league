package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TeamDAO : JpaRepository<Team, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Team t WHERE LOWER(t.name) = LOWER(:name)")
    fun existsByName(name: String): Boolean
}