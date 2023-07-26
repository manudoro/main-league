package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.PlayerDAO
import com.ar.mainleague.dao.PlayerMongoWrapper
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.PlayerSearchFilter
import com.ar.mainleague.service.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TeamServiceImpl : TeamService {

    @Autowired private lateinit var playerMongoWrapper : PlayerMongoWrapper
    @Autowired private lateinit var playerDao : PlayerDAO

    override fun researchPlayers(criteria: PlayerSearchFilter): List<Player> {
        val players = playerMongoWrapper.searchPlayers(criteria)
        val playersIds = players.map { p-> p.relId }
        return playerDao.findAllById(playersIds)

    }
}