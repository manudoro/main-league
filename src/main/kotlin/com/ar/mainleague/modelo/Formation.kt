package com.ar.mainleague.modelo

import com.ar.mainleague.service.exceptions.InvalidFormationException
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Formation(var defenders: Int, var midfielders: Int, var forwards: Int) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "formation", cascade = [CascadeType.ALL])
    var users : MutableSet<User> = mutableSetOf()

    init{
        validateFormation()
    }



    fun allowsPick(playersOnPosition: Int, position: Position): Boolean {
        val formacion = mapOf(
            Position.GOALKEEPER to 1,
            Position.DEFENSE to defenders,
            Position.MIDFIELDER to midfielders,
            Position.FORWARD to forwards
        )
        return formacion[position]!! > playersOnPosition
    }

    private fun validateFormation() {
        if(defenders + midfielders + forwards == 10){
            validateDefenders()
            validateMidfielders()
            validateForwards()
        }else{
            throw InvalidFormationException("Each formation must have ten players")
        }
    }

    private fun validateDefenders() {
        if(defenders !in 3..5){
            throw InvalidFormationException("Each formation must have between three and five defenders")
        }
    }

    private fun validateMidfielders() {
        if(midfielders !in 3..5){
            throw InvalidFormationException("Each formation must have between three and five midfielders")
        }
    }

    private fun validateForwards() {
        if(forwards !in 1..4){
            throw InvalidFormationException("Each formation must have between one and four forwards")
        }
    }


}
