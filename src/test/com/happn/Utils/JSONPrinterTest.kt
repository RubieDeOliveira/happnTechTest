package com.happn.Utils

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.happn.Data.InterestPoint
import com.happn.Data.Zone
import org.junit.jupiter.api.Test

class JSONPrinterTest {

    @Test
    fun `print a zone`(){
        val zone = Zone(-2.4, 7.8, 0.5)
        val res = JSONPrinter().print(zone)
        val expectedValue =  """
            {
              "min_lat": -2.5,
              "min_lon": 7.5,
              "max_lat": -2.0,
              "max_lon": 8.0
            }
        """.trimIndent()
        assertThat(res).isEqualTo(expectedValue)
    }

    @Test
    fun `print a value`(){
        val value = mapOf("value" to 2)
        val res = JSONPrinter().print(value)
        val expectedValue =  """
            {
              "value": 2
            }
        """.trimIndent()
        assertThat(res).isEqualTo(expectedValue)
    }

    @Test
    fun `print an array of zone`(){
        val zones = listOf(Zone(-2.4, 7.8, 0.5), Zone(-2.4, 7.8, 0.5))
        val res = JSONPrinter().print(zones)
        val expectedValue =  """
            [
              {
                "min_lat": -2.5,
                "min_lon": 7.5,
                "max_lat": -2.0,
                "max_lon": 8.0
              },
              {
                "min_lat": -2.5,
                "min_lon": 7.5,
                "max_lat": -2.0,
                "max_lon": 8.0
              }
            ]
        """.trimIndent()
        assertThat(res).isEqualTo(expectedValue)
    }
}