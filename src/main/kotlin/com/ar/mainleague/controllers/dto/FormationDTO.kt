package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.Formation

class FormationDTO(val defenders : Int, val midfielders : Int, val forwards : Int) {

    fun aModelo() : Formation {
        return Formation(defenders, midfielders, forwards)
    }

    companion object {
        fun desdeModelo(formation : Formation) =
            FormationDTO(
                defenders = formation.defenders,
                midfielders = formation.midfielders,
                forwards =  formation.forwards
            )
    }
}
