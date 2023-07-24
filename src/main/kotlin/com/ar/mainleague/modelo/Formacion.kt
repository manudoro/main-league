package com.ar.mainleague.modelo

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Formacion(var defenders: Int, var midfielders: Int, var forwards: Int) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "formacion", cascade = [CascadeType.ALL])
    var usuarios : MutableSet<User> = mutableSetOf()
}
