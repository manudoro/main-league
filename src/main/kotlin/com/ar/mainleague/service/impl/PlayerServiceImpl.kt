package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.PlayerDAO
import com.ar.mainleague.dao.PlayerMongoDAO
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.PlayerMongo
import com.ar.mainleague.modelo.Position
import com.ar.mainleague.modelo.utils.randomStats.StatsSetter
import com.ar.mainleague.service.PlayerService
import com.ar.mainleague.service.utils.EntentyUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PlayerServiceImpl : PlayerService, EntentyUtils(){
    @Autowired private lateinit var dao : PlayerDAO
    @Autowired private lateinit var mongoDao : PlayerMongoDAO

    override fun inscribePlayer(pos: Position, age: Int, name: String, lastName: String) : Player {
        val player = Player(pos, age, name, lastName)

        dao.save(player)
        val playerMongo = PlayerMongo(player.id!!, name, lastName, age, pos, player.rating!!)
        mongoDao.save(playerMongo)
        return player
    }

    override fun getPlayer(id: Long): Player {
        return this.findByIdOrThrow(dao, id)
    }

    override fun clear() {
        dao.deleteAll()
        mongoDao.deleteAll()
    }

}