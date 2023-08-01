package com.ar.mainleague.controllers.dto

import com.ar.mainleague.modelo.Position

class FilterDTO(
    val name:String? = null,
    val lastName:String? = null,
    val fromAge: Int? = null,
    val toAge:Int? = null,
    val position: Position? = null,
    val fromRate: Double? = null,
    val toRate: Double? = null) {

}
