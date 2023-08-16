package com.ar.mainleague.service

import com.ar.mainleague.modelo.PlayerScore
import com.ar.mainleague.modelo.Round
import com.ar.mainleague.modelo.RoundTable

interface RoundService {

    fun playRound(scores : Map<Long, Int>): Round

    fun getRoundWinner(roundNo : Long) : Pair<String, Int?>

    fun getRoundTable(roundNo: Long) : RoundTable

    fun getScoresByRound(roundNo: Long) : Map<Long, Int>

    fun getTopScores(roundNo : Long) : List<PlayerScore>
}