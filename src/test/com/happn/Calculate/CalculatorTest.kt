package com.happn.Calculate

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.happn.Data.InterestPoint
import com.happn.Data.Zone
import com.happn.Utils.TSVReader
import org.junit.jupiter.api.Test
import java.lang.Math.exp
import java.lang.Math.round
import java.math.RoundingMode
import java.text.DecimalFormat

class CalculatorTest {
    val reader = TSVReader("src/resources/test.tsv")
    val POis = Calculator(reader.read())

    @Test
    fun `calculate how many POis then have 3`(){
        val count = POis.nbPoints(-2.5, 38.0)
        assertThat(count).isEqualTo(3)
    }

    @Test
    fun `calculate zones`(){
        val result = POis.densest(0.5)
        assertThat(result.size).isEqualTo(3)
    }

    @Test
    fun `calculate zones and have right order`(){
        val result = POis.densest(0.5)
        val keys = result.keys.toList()
        assertThat(keys).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `calculate zones and have right values`(){
        val result = POis.densest(0.5)
        val keys = result.entries.toList()
        val first = keys[0].value[0]
        assertThat(first.maxlon()).isEqualTo(38.5)
        assertThat(first.minlon()).isEqualTo(38.0)
        assertThat(first.maxlat()).isEqualTo(-2.0)
        assertThat(first.minlat()).isEqualTo(-2.5)
    }

    @Test
    fun `calculate zones and have right values - 2`(){
        val result = POis.densest(0.5)
        val keys = result.entries.toList()
        assertThat(keys.size).isEqualTo(3)
        assertThat(keys[2].value.size).isEqualTo(3)
        val first = keys[2].value[1]
        assertThat(first.maxlon()).isEqualTo(8.5)
        assertThat(first.minlon()).isEqualTo(8.0)
        assertThat(first.maxlat()).isEqualTo(-27.0)
        assertThat(first.minlat()).isEqualTo(-27.5)
    }

    @Test
    fun `have the right count`(){
        val result = POis.densestN(2, 0.5)
        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun `have 4 first zones`(){
        val result = POis.densestN(4, 0.5)
        assertThat(result.size).isEqualTo(4)
    }

}