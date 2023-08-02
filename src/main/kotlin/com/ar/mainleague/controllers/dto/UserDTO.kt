package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.User

class UserDTO(
    val nickname : String,
    val formacion : FormationDTO
) {
    companion object {
        fun desdeModelo(user: User) =
            UserDTO(
                nickname = user.nickname,
                formacion = FormationDTO.desdeModelo(user.formation)
            )
    }
}