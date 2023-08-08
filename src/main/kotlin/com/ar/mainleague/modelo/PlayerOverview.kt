package com.ar.mainleague.modelo

import com.ar.mainleague.modelo.utils.randomStats.StatsSetter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("player")
class PlayerOverview(
        var name: String,
        var lastName: String,
        var age: Int,
        var position: Position
) {
     @Id
     var id : String? = null
     var relId: Long? = null
     var attack : Int = 0
     var defense : Int = 0
     var passing : Int = 0
     var gkSkills : Int = 0

     var rating : Double = 0.0

     init{
          StatsSetter.setAttributes(this)
     }

}
