package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.RoundTable

class TableDTO(users : List<RoundUserDTO>, roundNo : Long, roundWinner : RoundUserDTO) {


    companion object {
        fun desdeModelo(table : RoundTable): TableDTO = TableDTO(
            users = table.scores.entries.map { entry -> RoundUserDTO(entry.key, entry.value) },
            roundNo = table.round,
            roundWinner = RoundUserDTO(table.roundWinner!!.first, table.roundWinner!!.second)
        )
    }
}
