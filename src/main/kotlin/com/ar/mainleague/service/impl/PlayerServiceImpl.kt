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
    //@Autowired private lateinit var mongoDao : PlayerMongoWrapper


    override fun inscribePlayer(pos: Position, age: Int, name: String, lastName: String, rating: Double) : Player {
        //val playerOverview = PlayerOverview(name, lastName, age, pos)
        var player = Player(pos, age, name, lastName, rating)
        //player = dao.save(player)
        //playerOverview.relId = player.id
        //mongoDao.save(playerOverview)
        return dao.save(player)
    }

    override fun getPlayer(id:Long): Player{
        return this.findByIdOrThrow(dao, id)
    }

    /*override fun getPlayerStats(id: Long): PlayerOverview {
        return mongoDao.findByRelId(id)
    }

     */



    override fun getAll(): List<Player> {
        return dao.findAll()
    }

    /*override fun researchPlayers(criteria: PlayerSearchFilter): List<PlayerOverview> {
        return mongoDao.searchPlayers(criteria)

    }

     */

    override fun clear() {
        dao.deleteAll()
        //mongoDao.deleteAll()
    }

}