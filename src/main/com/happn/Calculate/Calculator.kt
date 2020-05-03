package com.happn.Calculate

import com.happn.Data.InterestMap
import com.happn.Data.Zone


class Calculator(private val POis : InterestMap){

    init {
        POis.sortByLat()
    }

    companion object {
        fun round(number : Double): Double {
            val intPart = kotlin.math.round(number)
            if (number < 0 ){
                if (number > intPart + 0.5) {
                    return intPart + 0.5
                }
                if (number < intPart)
                    return intPart - 0.5
            }
            else if (number > 0 && number < intPart ){
                return intPart - 0.5
            }
            return intPart
        }
    }

    //TODO : Use dichotomical algorithm
    fun nbPoints(minlat: Double, minLon: Double): Int{
        var counter = 0;
        for (point in POis.list()){
            if ((point.getLat() < minlat || point.getLon() < minLon) && counter == 0)
                continue
            else if ((point.getLat() < minlat || point.getLon() < minLon) && counter != 0)
                return counter
            else
                counter++
        }
        return counter
    }

    fun densest(jump: Double): Map<Int, List<Zone>>{
        var zone = Zone(POis.first().getLat(), POis.first().getLon(), jump)
        val res = sortedMapOf<Int, MutableList<Zone>>()
        var count = 1


        for (point in POis.list()){
            //skip the first point
            if (point == POis.first())
                continue

            val currentLat = point.getLat()
            val currentLon = point.getLon()

            if (currentLat > zone.maxlat()){
                if (count !in res.keys) res[count] = mutableListOf<Zone>()
                res[count]?.add(zone)

                zone = Zone(point.getLat(), point.getLon(), jump)
                count = 1
            }
            else {
                if (zone.minlon() == zone.maxlon()){
                    //outbound zone
                    if (currentLon < zone.minlon() - jump || currentLon > zone.maxlon() + jump){
                        if (count !in res.keys) res[count] = mutableListOf<Zone>()
                        res[count]?.add(zone)

                        zone = Zone(point.getLat(), point.getLon(), jump)
                        count = 1
                    }
                    // new max_lon
                    else if (currentLon >= zone.minlon() && currentLon <= zone.minlon() + jump){
                        count++
                        zone.setMaxLon()
                    }
                    //new min_lon
                    else if (currentLon <= zone.maxlon() && currentLon >= zone.maxlon() - jump){
                        count++
                        zone.setMinLon()
                    }
                }
                else {
                    if (currentLon < zone.minlon() || currentLon > zone.maxlon()){
                        if (count !in res.keys) res[count] = mutableListOf<Zone>()
                        res[count]?.add(zone)

                        zone = Zone(point.getLat(), point.getLon(), jump)
                        count = 1
                    }
                    else {
                        count++
                    }
                }
            }
        }
        if (count !in res.keys) res[count] = mutableListOf<Zone>()
        res[count]?.add(zone)
        return res.toSortedMap(Comparator.reverseOrder())
    }

    fun densestN(n: Int, jump: Double): List<Zone> {
        val res = mutableListOf<Zone>()
        val zones = densest(jump)
        var count = n
        for ((_, value) in zones){
            for (zone in value){
                res.add(zone)
                count --
                if (count <= 0) break
            }
            if (count <= 0) break
        }
        return res
    }

}