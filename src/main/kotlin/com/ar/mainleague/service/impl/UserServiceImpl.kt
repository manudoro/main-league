package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.FormacionDAO
import com.ar.mainleague.dao.PlayerDAO
import com.ar.mainleague.dao.PlayerMongoWrapper
import com.ar.mainleague.dao.UserDAO
import com.ar.mainleague.modelo.Formacion
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.User
import com.ar.mainleague.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl : UserService {

    @Autowired private lateinit var playerMongoWrapper : PlayerMongoWrapper
    @Autowired private lateinit var playerDao : PlayerDAO
    @Autowired private lateinit var formacionDao : FormacionDAO
    @Autowired private lateinit var userDao : UserDAO

    override fun researchPlayers(criteria: PlayerSearchFilter): List<Player> {
        val players = playerMongoWrapper.searchPlayers(criteria)
        val playersIds = players.map { p-> p.relId }
        return playerDao.findAllById(playersIds)

    }
    override fun createUser(nickname: String, formacion: Formacion) : User {

        val formacionPers = formacionDao.findByDefendersAndMidfieldersAndAndForwards(formacion.defenders, formacion.midfielders, formacion.forwards)
        if(formacionPers == null){

            }
        )
    }

    override fun pickPlayer(userId: Long, playerId: Long) {
        TODO("Not yet implemented")
    }

    override fun changeFormacion(userId: Long, formacion: Formacion) {
        TODO("Not yet implemented")
    }

    override fun chagePlayer(userId: Long, playerOutId: Long, playerInId: Long) {
        TODO("Not yet implemented")
    }

}