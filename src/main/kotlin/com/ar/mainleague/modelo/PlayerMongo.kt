package com.ar.mainleague.modelo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import kotlin.properties.Delegates

@Document("player")
class PlayerMongo(var relId: Long,
                  var name: String,
                  var lastName: String,
                  var age: Int,
                  var position: Position,
                  var rate: Double
) {
     @Id
     var id : String? = null

}
