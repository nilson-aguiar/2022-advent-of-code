package com.adventofcode.naguiar.day4

import com.adventofcode.naguiar.day4.service.CampParser
import com.adventofcode.naguiar.day4.service.CampService
import com.adventofcode.naguiar.day4.service.containsOrContainedIn
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MainTest {

    @Test
    fun `given a sample input, parser should generate 6 camps`() {
        val camps = CampParser("sample.txt").camps()

        assertThat(camps)
            .hasSize(6)
    }

    @Test
    fun `given IntRanges, contained in should provide the expected output`() {
        assertThat(Pair(IntRange(2, 8), IntRange(3, 7)).containsOrContainedIn()).isTrue
        assertThat(Pair(IntRange(3, 7), IntRange(2, 8)).containsOrContainedIn()).isTrue
        assertThat(Pair(IntRange(2, 6), IntRange(6, 6)).containsOrContainedIn()).isTrue
        assertThat(Pair(IntRange(2, 2), IntRange(2, 6)).containsOrContainedIn()).isTrue
        assertThat(Pair(IntRange(6, 6), IntRange(2, 6)).containsOrContainedIn()).isTrue
        assertThat(Pair(IntRange(9, 93), IntRange(10, 10)).containsOrContainedIn()).isTrue



        assertThat(Pair(IntRange(2, 4), IntRange(5, 7)).containsOrContainedIn()).isFalse
        assertThat(Pair(IntRange(5, 7), IntRange(7, 9)).containsOrContainedIn()).isFalse
    }

    @Test
    fun `given the sample input, should find 2 fully contained ranges`() {
        assertThat(CampService(CampParser("sample.txt")).fullyContainedPairsCount()).isEqualTo(2)
    }

    @Test
    fun `given a sample input, parser should generate the correct IntRanges`() {
        val intRanges = CampParser("sample.txt").camps().flatMap { it.elfList }.map { it.sectionsAsIntRage() }

        assertThat(intRanges).containsExactly(
            IntRange(2, 4), IntRange(6, 8),
            IntRange(2, 3), IntRange(4, 5),
            IntRange(5, 7), IntRange(7, 9),
            IntRange(2, 8), IntRange(3, 7),
            IntRange(6, 6), IntRange(4, 6),
            IntRange(2, 6), IntRange(4, 8)
        )
    }

}
