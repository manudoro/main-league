package com.ar.mainleague.modelo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("round")
class Round(@Indexed(unique = true) val round: Long, val scores : Map<Long, Int>) {

    @Id
    var id : String? = null



}