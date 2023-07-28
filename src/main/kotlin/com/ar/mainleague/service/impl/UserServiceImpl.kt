package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.FormacionDAO
import com.ar.mainleague.dao.PlayerDAO
import com.ar.mainleague.dao.PlayerMongoWrapper
import com.ar.mainleague.dao.UserDAO
import com.ar.mainleague.dao.utils.EntityUtils
import com.ar.mainleague.modelo.Formation
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.PlayerSearchFilter
import com.ar.mainleague.modelo.User
import com.ar.mainleague.service.UserService
import com.ar.mainleague.service.exceptions.InvalidPickExecption
import com.ar.mainleague.service.exceptions.InvalidSubstitutionException
import com.ar.mainleague.service.exceptions.UniqueException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl : UserService, EntityUtils() {

    @Autowired private lateinit var playerMongoWrapper : PlayerMongoWrapper
    @Autowired private lateinit var playerDao : PlayerDAO
    @Autowired private lateinit var formationDao : FormacionDAO
    @Autowired private lateinit var userDao : UserDAO


    override fun researchPlayers(criteria: PlayerSearchFilter): List<Player> {
        val players = playerMongoWrapper.searchPlayers(criteria)
        val playersIds = players.map { p-> p.relId }
        return playerDao.findAllById(playersIds)

    }
    override fun createUser(nickname: String, formacion: Formation) : User {
        if(userDao.existsByNickname(nickname)){
            throw UniqueException("The nickname just exists, please choose another one.")
        }
        var formationToUse = createIfNotExists(formacion)
        return userDao.save(User(nickname, formationToUse))
    }

    override fun getUser(userId: Long): User {
        return this.findByIdOrThrow(userDao, userId)
    }

    override fun getPlayers(userId: Long): List<Player> {
        return userDao.getPlayersByUser(userId)
    }

    private fun createIfNotExists(formation: Formation): Formation {
        val formationPers = formationDao.findByDefendersAndMidfieldersAndAndForwards(formation.defenders, formation.midfielders, formation.forwards)
        return if(formationPers != null){formationPers} else{
            formationDao.save(formation)
        }
    }

    override fun pickPlayer(userId: Long, playerId: Long) {
        val user = this.findByIdOrThrow(userDao, userId)
        val player = this.findByIdOrThrow(playerDao, playerId)
        val playersOnPosition = playerDao.countByPositionAndUserId(player.position, userId)
        val formation = user.formation
        if(formation.allowsPick(playersOnPosition, player.position)){
            user!!.pick(player)
            userDao.save(user)
        } else{
            throw InvalidPickExecption("Too much players on that position.")
        }

    }

    override fun changeFormation(userId: Long, formation: Formation) {
        val user = this.findByIdOrThrow(userDao, userId)
        user.changeFormation(createIfNotExists(formation))
        userDao.save(user)

    }

    override fun getFormation(userId: Long): Formation {
        return formationDao.findByUserId(userId)
    }

    override fun substitutePlayer(userId: Long, playerOutId: Long, playerInId: Long) {
        if(playerInId != playerOutId && playerDao.existsByIdInUserTeam(playerOutId, userId)){
            val playerIn = this.findByIdOrThrow(playerDao, playerInId)
            val playerOut = this.findByIdOrThrow(playerDao, playerOutId)
            val user = this.findByIdOrThrow(userDao, userId)
            user.changePlayer(playerIn, playerOut)
            userDao.save(user)
        } else {
            throw InvalidSubstitutionException("Cannot substitute")
        }
    }

}