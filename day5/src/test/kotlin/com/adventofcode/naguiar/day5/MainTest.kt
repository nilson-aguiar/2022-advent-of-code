package com.adventofcode.naguiar.day5

import com.adventofcode.naguiar.day5.domain.CraneInstructions
import com.adventofcode.naguiar.day5.domain.CraneMove
import com.adventofcode.naguiar.day5.domain.Crate
import com.adventofcode.naguiar.day5.domain.CrateStack
import com.adventofcode.naguiar.day5.domain.Ship
import com.adventofcode.naguiar.day5.service.CraneMoveMode
import com.adventofcode.naguiar.day5.service.CraneService
import com.adventofcode.naguiar.day5.service.InstructionsParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MainTest {


    @Test
    fun `given a sample should print parse the expected Ship`() {
        val expectedShip = Ship(
            mapOf(
                1 to CrateStack(listOf(Crate('N'), Crate('Z'))),
                2 to CrateStack(listOf(Crate('D'), Crate('C'), Crate('M'))),
                3 to CrateStack(listOf(Crate('P')))
            )
        )

        val actual = InstructionsParser("sample.txt").ship

        assertThat(actual).isEqualTo(expectedShip)
    }

    @Test
    fun `given a sample should print parse the expected instructions`() {
        val expectedCraneInstructions = CraneInstructions(
            listOf(
                CraneMove(1, 2, 1),
                CraneMove(3, 1 ,3),
                CraneMove(2, 2, 1),
                CraneMove(1, 1, 2)
            )
        )

        val actual = InstructionsParser("sample.txt").instructions


        assertThat(actual).isEqualTo(expectedCraneInstructions)
    }

    @Test
    fun `given a ship and craneInstructions, when moving crates individually, should move the crates and generated the expected ship state`() {
        val expectedShip = Ship(
            mapOf(
                1 to CrateStack(listOf(Crate('C'))),
                2 to CrateStack(listOf(Crate('M'))),
                3 to CrateStack(listOf(Crate('Z'), Crate('N'), Crate('D'), Crate('P')))
            )
        )

        val parser = InstructionsParser("sample.txt")

        val actual = CraneService().executeInstructions(parser.ship, parser.instructions, CraneMoveMode.INDIVIDUALLY)

        assertThat(actual).isEqualTo(expectedShip)
    }

    @Test
    fun `given a ship and craneInstructions, when moving crates grouped, should move the crates and generated the expected ship state`() {
        val expectedShip = Ship(
            mapOf(
                1 to CrateStack(listOf(Crate('M'))),
                2 to CrateStack(listOf(Crate('C'))),
                3 to CrateStack(listOf(Crate('D'), Crate('N'), Crate('Z'), Crate('P')))
            )
        )

        val parser = InstructionsParser("sample.txt")

        val actual = CraneService().executeInstructions(parser.ship, parser.instructions, CraneMoveMode.GROUPED)

        assertThat(actual).isEqualTo(expectedShip)
    }
}
