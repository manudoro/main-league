package com.ar.mainleague.controllers.dto

class RoundUserDTO(val nickname : String, val score : Int?  ) {
    companion object {
        fun desdeModelo(player: Pair<String, Int?>): RoundUserDTO =
            RoundUserDTO(
                nickname =  player.first,
                score = player.second
            )
    }


}