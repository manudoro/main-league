package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.PlayerMongo
import com.ar.mainleague.modelo.User
import com.ar.mainleague.service.UserService

class UserDTO(
    val nickname : String,
    val formacion : FormationDTO,
    val players : List<PlayerDTO>
) {
    companion object {
        fun desdeModelo(user: User, players: List<PlayerMongo>) =
            UserDTO(
                nickname = user.nickname,
                formacion = FormationDTO.desdeModelo(user.formation),
                players = players.map { p -> PlayerDTO.desdeModelo(p) }
            )
    }
}