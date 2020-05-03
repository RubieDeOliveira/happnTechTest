package com.happn.Data

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.happn.Utils.TSVReader
import org.junit.jupiter.api.Test

class InterestMapTest {
    private val reader = TSVReader("src/resources/test.tsv")
    private val POis = reader.read()

    @Test
    fun `test to sort list by latitude`(){
        POis.sortByLat()
        assertThat(POis.last().getId()).isEqualTo("id5")
        assertThat(POis.last().getLat()).isEqualTo(6.8)
        assertThat(POis.last().getLon()).isEqualTo(-6.9)
    }

}