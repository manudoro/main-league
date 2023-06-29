package com.ar.mainleague.modelo.utils

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.utils.exceptions.BlankFieldException
import com.ar.mainleague.modelo.utils.exceptions.InvalidAgeException

class Validator {
    companion object {
        fun validatePlayer(player: Player) {
            if (player.name.isBlank() || player.lastName.isBlank()){
                throw BlankFieldException("Player name and last name must not be blank")
            }
            if (player.age !in (18..45)){
                throw InvalidAgeException("Player age must be between 18 and 45")
            }
        }
    }


}
