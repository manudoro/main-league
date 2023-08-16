package com.ar.mainleague.service.impl

import com.ar.mainleague.dao.RoundDAO
import com.ar.mainleague.dao.RoundTableDAO
import com.ar.mainleague.dao.UserDAO
import com.ar.mainleague.modelo.Round
import com.ar.mainleague.modelo.RoundTable
import com.ar.mainleague.modelo.User
import com.ar.mainleague.service.RoundService
import com.ar.mainleague.service.exceptions.NotFoundException
import com.ar.mainleague.service.utils.CounterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RoundServiceImpl(private val counterService: CounterService) : RoundService {

    @Autowired lateinit var dao : RoundDAO
    @Autowired lateinit var tableDao : RoundTableDAO
    @Autowired lateinit var userDao : UserDAO
    override fun playRound(scores: Map<Long, Int>) : Round {
        var roundNo = counterService.findMaxValueForField("round") ?: 0
        roundNo = roundNo + 1
        var round = Round(roundNo, scores)
        round = dao.save(round)
        val usersNicknames =  userDao.getNicknamesReady()
        val usersTable = mutableMapOf<String,Int?>()
        usersNicknames.forEach { n -> usersTable[n] = sumUserScores(userDao.getPlayersIdByUser(n), scores)}
        val table = RoundTable(round.round, usersTable)
        tableDao.save(table)
        return dao.save(round)
    }

    override fun getRoundWinner(roundNo: Long): Pair<String, Int?> {
        val roundTable = tableDao.findRoundTablesByRound(roundNo) ?: throw NotFoundException("Round $roundNo not found.")
        return roundTable.roundWinner ?: throw NotFoundException("Round $roundNo not found.")
    }

    override fun getRoundTable(roundNo: Long): RoundTable {
        return tableDao.findRoundTablesByRound(roundNo) ?: throw NotFoundException("Round $roundNo not found.")
    }

    override fun getScoresByRound(roundNo: Long): Map<Long, Int> {
        val round = dao.findByRound(roundNo) ?: throw NotFoundException("Round $roundNo not found.")
        return round.scores
    }

    private fun sumUserScores(playersId: List<Long>, scores: Map<Long, Int>): Int {
        return playersId.mapNotNull { i -> scores[i] }.sum()
    }

}