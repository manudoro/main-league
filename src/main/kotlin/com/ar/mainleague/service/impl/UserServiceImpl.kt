package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.*
import com.ar.mainleague.dao.utils.EntityUtils
import com.ar.mainleague.modelo.Formation
import com.ar.mainleague.modelo.PlayerMongo
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

    @Autowired private lateinit var playerDao : PlayerDAO
    @Autowired private lateinit var mongoDao : PlayerMongoWrapper
    @Autowired private lateinit var formationDao : FormacionDAO
    @Autowired private lateinit var userDao : UserDAO



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

    override fun getUserByNickname(nickname: String): User {
        return userDao.findByNickname(nickname) ?: throw NoSuchElementException("User $nickname not found")
    }

    override fun getPlayers(nickname: String): List<PlayerMongo> {
        val ids = userDao.getPlayersByUser(nickname)
        return mongoDao.findAllByIdIn(ids)
    }

    private fun createIfNotExists(formation: Formation): Formation {
        val formationPers = formationDao.findByDefendersAndMidfieldersAndAndForwards(formation.defenders, formation.midfielders, formation.forwards)
        return if(formationPers != null){formationPers} else{
            formationDao.save(formation)
        }
    }

    override fun pickPlayer(nickname: String, playerId: Long) {
        val user = this.getUserByNickname(nickname)
        val player = this.findByIdOrThrow(playerDao, playerId)
        val playersOnPosition = playerDao.countByPositionAndUserId(player.position, user.id!!)
        val formation = user.formation
        if(formation.allowsPick(playersOnPosition, player.position)){
            user.pick(player)
            userDao.save(user)
        } else{
            throw InvalidPickExecption("Too much players on that position.")
        }

    }

    override fun changeFormation(nickname: String, formation: Formation) {
        val user = this.getUserByNickname(nickname)
        user.changeFormation(createIfNotExists(formation))
        userDao.save(user)

    }

    override fun getFormation(nickname: String): Formation {
        return formationDao.findByUserId(nickname)
    }

    override fun substitutePlayer(nickname: String, playerOutId: Long, playerInId: Long) {
        if(playerInId != playerOutId && playerDao.existsByIdInUserTeam(playerOutId, nickname)){
            val playerIn = this.findByIdOrThrow(playerDao, playerInId)
            val playerOut = this.findByIdOrThrow(playerDao, playerOutId)
            val user = this.getUserByNickname(nickname)
            user.changePlayer(playerIn, playerOut)
            userDao.save(user)
        } else {
            throw InvalidSubstitutionException("Cannot substitute")
        }
    }

}