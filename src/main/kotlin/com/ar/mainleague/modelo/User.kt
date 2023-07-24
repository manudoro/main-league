package com.ar.mainleague.modelo

import javax.persistence.*

@Entity
class User(@Column(unique = true) var nickname: String, @ManyToOne var formacion: Formacion) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany
    var players : MutableSet<Player> = mutableSetOf()




}