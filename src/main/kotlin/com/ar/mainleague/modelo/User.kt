package com.ar.mainleague.modelo

import javax.persistence.*

@Entity
class User(@Column(unique = true) var nickname: String, @ManyToOne var formation: Formation) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var budget : Double = 700.0

    var readyToPlay : Boolean = false

    @ManyToMany(mappedBy = "users", cascade = [CascadeType.ALL],  fetch = FetchType.EAGER)
    var players : MutableSet<Player> = mutableSetOf()

    fun pick(player: Player) {
        players.add(player)
        player.addUser(this)
        if(fullTeam()){
            readyToPlay = true
        }
    }

    fun changeFormation(formation: Formation) {
        this.formation = formation
        this.players.forEach{p -> p.removeUserById(this.id!!)}
        this.players.clear()
        this.readyToPlay = false
        resetBudget()

    }

    private fun resetBudget() {
        budget = 700.0
    }

    fun changePlayer(playerIn : Player, playerOut : Player) {
            paySubstitution(playerIn.rating, playerOut.rating)
            players.removeIf { p -> p.id == playerOut.id }
            players.add(playerIn)
            playerOut.removeUserById(this.id!!)
            playerIn.addUser(this)

    }

    private fun paySubstitution(playerInRating: Double, playerOutRating: Double) {
        budget += (playerOutRating - playerInRating)
    }

    fun pay(rating: Double){
        budget -= rating
    }

    fun canAfford(rating: Double): Boolean {
        return budget >= rating

    }

    fun fullTeam(): Boolean {
        return this.players.size == 11
    }


}