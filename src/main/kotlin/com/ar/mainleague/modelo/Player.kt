package com.ar.mainleague.modelo

import com.ar.mainleague.modelo.utils.Validator
import com.ar.mainleague.modelo.utils.randomStats.StatsSetter
import javax.persistence.*

@Entity
data class Player(
    var position: Position,
    var age: Int,
    var name: String,
    var lastName: String) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
    var attack : Int = 0
    var defense : Int = 0
    var passing : Int = 0
    var gkSkills : Int = 0

    var rating : Double = 0.0

    init{
        StatsSetter.setAttributes(this)
        Validator.validatePlayer(this)
    }

    @ManyToMany
    var users : MutableSet<User> = mutableSetOf()

    fun addUser(user: User) {
        users.add(user)
    }

    fun removeUser(user: User) {
        users.removeIf{u-> u.id == user.id}

    }


}