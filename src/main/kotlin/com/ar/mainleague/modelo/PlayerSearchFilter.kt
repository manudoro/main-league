package com.ar.mainleague.modelo

import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class PlayerSearchFilter(
    private val name:String? = null,
    private val lastName:String? = null,
    private val fromAge: Int? = null,
    private val toAge:Int? = null,
    private val position: Position? = null,
    private val fromRate: Double? = null,
    private val toRate: Double? = null) {

    private val criteria = Criteria()

    private fun mapToCriteria(){
        name?.let{criteria.and("name").`is`(it)}
        lastName?.let{criteria.and("lastName").`is`(it)}
        fromAge?.let { criteria.and("age").gt(it) }
        toAge?.let { criteria.and("age").lt(it) }
        position?.let {criteria.and("position").`is`(it)}
        fromRate?.let { criteria.and("rate").gt(it) }
        toRate?.let { criteria.and("rate").lt(it) }
    }

    init {
        mapToCriteria()
    }

    fun getQuery(): Query{
        return Query(criteria)
    }
}
