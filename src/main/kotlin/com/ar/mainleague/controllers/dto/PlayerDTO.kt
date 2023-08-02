package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.PlayerMongo
import com.ar.mainleague.modelo.Position

class PlayerDTO(
    val playerId : Long?,
    val position : Position,
    val age : Int,
    val name : String,
    val lastName : String,
    val rating : Double) {

    fun aModelo() : Player {
        val player = Player(position, age, name, lastName)
        if(playerId != null){
            player.id = playerId
        }
        return player
    }

    companion object{
        fun desdeModelo(player : PlayerMongo) =
            PlayerDTO(
                playerId = player.relId,
                position = player.position,
                age = player.age,
                name = player.name,
                lastName = player.lastName,
                rating = player.rating
            )


        }
}

