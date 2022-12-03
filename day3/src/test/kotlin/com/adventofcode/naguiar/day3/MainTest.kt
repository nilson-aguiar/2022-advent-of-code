package com.adventofcode.naguiar.day3

import com.adventofcode.naguiar.day3.service.RucksackParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MainTest {

    @Test
    fun `given a sample input, should parse the rucksacks correctly`() {
        val rucksacks = RucksackParser("sample.txt").parseRucksacks()
        val sumOfPriorities = rucksacks.sumPriorities()

        assertThat(rucksacks)
            .hasSize(6)
        assertThat(sumOfPriorities).isEqualTo(157)
    }

    @Test
    fun `given a sample input, when grouped by 3, should parse the rucksacks correctly`() {
        val rucksacks = RucksackParser("sample.txt").parseRucksacks()
        val groups = rucksacks.findBadges(3)

        assertThat(groups)
            .hasSize(2)

        assertThat(groups.sumCharAsPriorities()).isEqualTo(70)
    }

}