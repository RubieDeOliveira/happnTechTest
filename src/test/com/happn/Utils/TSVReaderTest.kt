package com.happn.Utils

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TSVReaderTest {

    @Test
    fun `can read file`(){
        val reader = TSVReader("src/resources/test.tsv")
        val map = reader.read()
        assertThat(map).isNotNull()
    }

    @Test
    fun `generate a list of 8 POis`(){
        val reader = TSVReader("src/resources/test.tsv")
        val map = reader.read()
        assertThat(map.size()).isEqualTo(8)
    }

    @Test
    fun `wrong format in file then throw exception`(){
        assertThrows<FormatException>{
            val reader = TSVReader("src/resources/exception.tsv")
            reader.read()
        }
    }
}