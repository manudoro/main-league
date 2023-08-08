package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.PlayerMongoWrapper
import com.ar.mainleague.dao.RoundDAO
import com.ar.mainleague.modelo.Round
import com.ar.mainleague.service.RoundService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RoundServiceImpl : RoundService {
    @Autowired private lateinit var playerDao : PlayerMongoWrapper
    @Autowired private lateinit var roundDao : RoundDAO
    override fun playRound() : Round {
        val players = playerDao.findAll()
        val round = Round()
        round.ratePlayers(players)
        return roundDao.save(round)

    }
}