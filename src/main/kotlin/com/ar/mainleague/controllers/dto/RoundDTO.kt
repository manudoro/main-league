package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.Round

class RoundDTO(val roundNo : Long?, val scores : Map<Long, Int>) {
    companion object {
        fun desdeModelo(round: Round): RoundDTO =
            RoundDTO(roundNo = round.round, scores = round.scores)
    }

}
