package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.PlayerDAO
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.Position
import com.ar.mainleague.service.PlayerService
import com.ar.mainleague.service.utils.EntentyUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PlayerServiceImpl : PlayerService, EntentyUtils(){
    @Autowired private lateinit var dao : PlayerDAO

    override fun inscribePlayer(pos: Position, age: Int, name: String, lastName: String, rating: Double) : Player {
        var player = Player(pos, age, name, lastName, rating)
        return dao.save(player)
    }

    override fun getPlayer(id:Long): Player{
        return this.findByIdOrThrow(dao, id)
    }

    override fun getPlayers(ids: List<Long>): List<Player> {
        return dao.findByIdIn(ids)
    }

    override fun getAll(): List<Player> {
        return dao.findAll()
    }

    override fun clear() {
        dao.deleteAll()
    }



}