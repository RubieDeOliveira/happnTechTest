package com.happn.Data

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.google.gson.annotations.Expose
import com.happn.Calculate.Calculator.Companion.round

@JsonSerialize
class Zone (minlat : Double, lon: Double, jumpValue: Double) {

    @Expose(serialize = false)
    private val jump = jumpValue

    @Expose(serialize = true)
    private var min_lat = minlat
    @Expose(serialize = true)
    private var min_lon = 0.0
    @Expose(serialize = true)
    private var max_lat = 0.0
    @Expose(serialize = true)
    private var max_lon = 0.0


    init {
        min_lat = round(min_lat)
        max_lat = min_lat + jump

        val rounded_lon = round(lon)
        if (lon < rounded_lon) {
            min_lon = rounded_lon - jump
            max_lon = rounded_lon
        }
        else if (lon > rounded_lon){
            min_lon = rounded_lon
            max_lon = rounded_lon + jump
        }
        else {
            min_lon = rounded_lon
            max_lon = rounded_lon
        }
    }

    fun minlat() : Double {return min_lat}
    fun minlon() : Double {return min_lon}
    fun maxlat() : Double {return max_lat}
    fun maxlon() : Double {return max_lon}

    fun setMaxLon() {
        if (max_lon == min_lon){
            max_lon += jump
        }
    }
    fun setMinLon() {
        if (max_lon == min_lon){
            min_lon -= jump
        }
    }
}