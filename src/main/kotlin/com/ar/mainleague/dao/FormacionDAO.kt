package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Formation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FormacionDAO : JpaRepository<Formation, Long> {

    fun findByDefendersAndMidfieldersAndAndForwards(defenders : Int, midfielders : Int, forwards : Int) : Formation?

    @Query("SELECT f FROM Formation f JOIN f.users u WHERE u.nickname = :nickname")
    fun findByUserId(nickname: String): Formation




}