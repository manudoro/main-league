package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.Player

class RoundPlayerDTO(val player : PlayerDTO , val score : Int) {
    companion object {
        fun desdeModelo(player: Player, score: Int): RoundPlayerDTO {
            return RoundPlayerDTO(
                player = PlayerDTO.desdeModelo(player),
                score = score
            )
        }
    }
}