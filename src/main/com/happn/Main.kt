package com.happn

import com.google.gson.Gson
import com.happn.Calculate.Calculator
import com.happn.Data.InterestPoint
import com.happn.Data.NumberDensest
import com.happn.Utils.JSONPrinter
import com.happn.Utils.TSVReader
import java.io.File


fun main(args: Array<String>) {
    val defaultfilepath = "src/resources/test.tsv"
    val defaultMsg = """
    Missing arguments. Expected : 
      --nbpoi '{"min_lat": <DOUBLE>, "min_lon": <DOUBLE>}'
    OR
      --densest '{"n": <INT>}'
    """.trimIndent()

    println("Enter a valid filepath:")
    var filepath : String = readLine().toString()
    val f = File(filepath)
    if (!f.exists() || f.isDirectory()){
        println("Wrong path, by default we use the given file for technical test.")
        filepath = defaultfilepath
    }

    println("Enter command you want :")
    val arguments = readLine()?.split("'")?.toMutableList()
    arguments?.removeAt(arguments.size - 1)

    if (arguments != null) {
        if (arguments.size != 2){
            println(defaultMsg)
            return
        }
    }

    val jump = 0.5
    val reader = TSVReader(filepath)
    val calculator = Calculator(reader.read())
    val printer = JSONPrinter()

    //--nbpoi '{"min_lat": 6.5, "min_lon": -7}'
    if (arguments!![0] == "--nbpoi "){
        val info = Gson().fromJson(arguments[1], InterestPoint::class.java)
        val res = calculator.nbPoints(info.getLat(), info.getLon())
        println(printer.print(mapOf("value" to res)))
    }
    //--densest '{"n": 2}'
    else if (arguments[0] == "--densest "){
        val number = Gson().fromJson(arguments[1], NumberDensest::class.java)
        //val number = arguments[1] //to parse with json
        val res = calculator.densestN(number.n, jump)
        println(printer.print(res))
    }
    else {
        println(defaultMsg)
    }
}