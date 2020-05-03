package com.happn.Data

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
class InterestPoint (private val id: String, private val min_lat: Double, private val min_lon: Double) {

    fun getId(): String { return this.id}
    fun getLat(): Double { return this.min_lat}
    fun getLon(): Double { return this.min_lon}
    //fun display() {println("${id} ${lat} ${lon}")}
}