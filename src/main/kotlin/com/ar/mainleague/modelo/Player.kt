package com.ar.mainleague.modelo

import com.ar.mainleague.modelo.utils.randomStats.RandomStats
import java.util.InvalidPropertiesFormatException
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Player(var position: String, var age: Int,var name: String, var lastName: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
    var attack : Int = 0
    var defense : Int = 0
    var passing : Int = 0
    var gkSkills : Int = 0
    var rating : Float = 0.0F

    init{
        validatePosition()
        RandomStats.setAttributes(this)
        rating = rate()
    }

    private fun validatePosition() {
        if(position.uppercase() !in listOf("GK", "DF", "MF", "FW")){
            throw InvalidPropertiesFormatException("La posición no es valida")
        }
    }

    fun rate():Float{
        val co = (attack + defense + passing + gkSkills) / 3 - (age / 2)
        return co.toFloat()
    }


}