package com.happn.Utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.google.gson.GsonBuilder
import java.lang.reflect.Modifier


class JSONPrinter {
    val builder = GsonBuilder()
    val gson = builder
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    fun print(data: Any) : String{
        val res = gson.toJson(data)
        return res
        /*val mapper = ObjectMapper()
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        return mapper.writeValueAsString(data)*/
    }

}