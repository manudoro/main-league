package com.ar.mainleague.modelo.utils.randomStats

import com.ar.mainleague.modelo.Player
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

object RandomStats {


    private fun setGKAttributes(player: Player) {
        player.attack = (0..40).random()
        player.defense = (35..75).random()
        player.passing = (30..60).random()
        player.gkSkills = (75..99).random()
    }

    private fun setDFAttributes(player: Player) {
        player.attack = (40..75).random()
        player.defense = (70..99).random()
        player.passing = (40..75).random()
        player.gkSkills = (0..40).random()
    }

    private fun setMFAttributes(player: Player) {
        player.attack =   (50..90).random()
        player.defense =  (50..90).random()
        player.passing =  (50..90).random()
        player.gkSkills = (0.. 40).random()

    }

    private fun setFWAttributes(player: Player) {
        player.attack =   (70..99).random()
        player.defense =  (20..60).random()
        player.passing =  (65..85).random()
        player.gkSkills = (0.. 40).random()

    }

    fun setAttributes(player: Player) {
        when(player.position.uppercase()){
            "GK" -> setGKAttributes(player)
            "DF" -> setDFAttributes(player)
            "MF" -> setMFAttributes(player)
            "FW" -> setFWAttributes(player)
        }

    }
}