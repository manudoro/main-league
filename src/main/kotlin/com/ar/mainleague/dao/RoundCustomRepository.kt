package com.ar.mainleague.dao


interface RoundCustomRepository {

    fun sumScoresByKeysForDocument(roundId: String, ids: List<Long>): Int
}
