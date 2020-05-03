package com.happn.Data

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.happn.Calculate.Calculator.Companion.round
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ZoneTest {

    @Test
    fun `have the right round`(){
        val zone = Zone(2.6, 34.5, 0.5)
        assertThat(zone.minlat()).isEqualTo(2.5)
        assertThat(zone.maxlat()).isEqualTo(3.0)
    }

    @Test
    fun `check have the round to the int part with negative number`(){
        val zone = Zone(-48.6, -37.7, 0.5)
        assertThat(zone.minlat()).isEqualTo(-49.0)
        assertThat(zone.maxlat()).isEqualTo(-48.5)
    }

    @Test
    fun `check have the round to the decimal part with negative number`(){
        val zone = Zone(-27.1, 8.4, 0.5)
        assertThat(zone.minlat()).isEqualTo(-27.5)
        assertThat(zone.maxlat()).isEqualTo(-27.0)
    }

    @Test
    fun `test with positive number round to int part`(){
        val zone = Zone(38.3, -2.3, 0.5)
        assertThat(zone.minlat()).isEqualTo(38.0)
        assertThat(zone.maxlat()).isEqualTo(38.5)
    }

    @Test
    fun `test with positive number round to decimal part`(){
        val zone = Zone(38.6, -2.3, 0.5)
        assertThat(zone.minlat()).isEqualTo(38.5)
        assertThat(zone.maxlat()).isEqualTo(39.0)
    }

    @Test
    fun `check zone constructor is ok`(){
        val zone = Zone(-48.6, -37.7, 0.5)
        assertThat(zone.minlat()).isEqualTo(-49.0)
        assertThat(zone.maxlat()).isEqualTo(-48.5)
        assertThat(zone.minlon()).isEqualTo(-38.0)
        assertThat(zone.maxlon()).isEqualTo(-37.5)
    }


}