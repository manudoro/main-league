package com.ar.mainleague.modelo

import com.ar.mainleague.modelo.exceptions.InvalidReplacementException
import javax.persistence.*

@Entity
class User(@Column(unique = true) var nickname: String, @ManyToOne var formation: Formation) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany(mappedBy = "users", cascade = [CascadeType.ALL],  fetch = FetchType.EAGER)
    var players : MutableSet<Player> = mutableSetOf()

    fun pick(player: Player) {
        players.add(player)
        player.addUser(this)
    }

    fun changeFormation(formation: Formation) {
        this.formation = formation
        this.players.clear()

    }

    fun changePlayer(playerIn : Player, playerOut : Player) {
        if(playerIn.position == playerOut.position){
            players.removeIf { p -> p.id == playerOut.id }
            players.add(playerIn)
            playerOut.removeUser(this)
            playerIn.addUser(this)
        } else{
            throw InvalidReplacementException("The players must play in the same position")
        }
    }


}