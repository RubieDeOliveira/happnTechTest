package com.happn.Data

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
class InterestMap (private var POis : List<InterestPoint>){

    fun sortByLat() {
        POis = POis.sortedBy { it.getLat() }
    }

    fun last(): InterestPoint {
        return POis[POis.size - 1]
    }

    fun first(): InterestPoint {
        return POis[0]
    }

    fun list(): List<InterestPoint>{
        return POis
    }

    fun size(): Int {
        return POis.size
    }

    /*fun display() {
        for (point in POis) {
            point.display()
        }
    }*/
}