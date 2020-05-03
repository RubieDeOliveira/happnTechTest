package com.happn.Utils

import com.happn.Calculate.Calculator
import com.happn.Data.InterestMap
import com.happn.Data.InterestPoint
import java.io.File
import java.io.InputStream

class TSVReader (private val filepath: String) {

    fun read() : InterestMap {
        val POis = mutableListOf<InterestPoint>()
        val inputStream: InputStream = File(filepath).inputStream()
        inputStream.bufferedReader().use {
            val lines = it.readText().split("\n")
            var first = 1
            for (line in lines) {
                val POisInfo = line.split("\t")
                if (POisInfo.size != 3){
                    throw FormatException("Invalid format in file. Expected 3 columns : @id, @lat, @lon")
                }
                if (first ==1 && (POisInfo[0] != "@id" || POisInfo[1] != "@lat" || POisInfo[2] != "@lon")){
                    throw FormatException("Invalid format in file. Expected 3 columns : @id, @lat, @lon")
                }
                if (first == 1){
                    first = 0
                    continue
                }
                POis.add(InterestPoint(POisInfo[0], POisInfo[1].toDouble(), POisInfo[2].toDouble()))
            }
        }
        return InterestMap(POis)
    }
}