package com.ar.mainleague.dao

import com.ar.mainleague.modelo.Formacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FormacionDAO : JpaRepository<Formacion, Long> {

    fun findByDefendersAndMidfieldersAndAndForwards(defenders : Int, midfielders : Int, forwards : Int) : Formacion?





}