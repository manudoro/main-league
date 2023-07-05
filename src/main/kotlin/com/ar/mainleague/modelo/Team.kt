package com.ar.mainleague.modelo

import javax.persistence.*

@Entity
class Team(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
/*
    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var players: MutableSet<Player> = mutableSetOf()

    var budget: Double = 10000.0

    fun signPlayer(player : Player){
        players.add(player)
    }

    fun releasePlayer(player : Player){
        players.remove(player)
    }

 */

}