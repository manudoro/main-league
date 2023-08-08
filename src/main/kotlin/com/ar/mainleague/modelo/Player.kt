package com.ar.mainleague.modelo

import com.ar.mainleague.modelo.utils.Validator
import javax.persistence.*

@Entity
data class Player(
    var position: Position,
    var age: Int,
    var name: String,
    var lastName: String,
    var rating: Double) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null

    init{
        Validator.validatePlayer(this)
    }

    @ManyToMany
    var users : MutableSet<User> = mutableSetOf()

    fun addUser(user: User) {
        users.add(user)
    }

    fun removeUserById(userId: Long) {
        users.removeIf{u-> u.id == userId}

    }


}