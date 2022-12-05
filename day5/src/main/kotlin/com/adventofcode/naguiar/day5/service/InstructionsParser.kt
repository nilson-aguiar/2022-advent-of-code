package com.adventofcode.naguiar.day5.service

import com.adventofcode.naguiar.day5.domain.CraneInstructions
import com.adventofcode.naguiar.day5.domain.CraneMove
import com.adventofcode.naguiar.day5.domain.Crate
import com.adventofcode.naguiar.day5.domain.CrateStack
import com.adventofcode.naguiar.day5.domain.Ship
import com.adventofcode.naguiar.readResourceAsText

class InstructionsParser(input: String) {

    private val shipParser: ShipParser
    private val craneParser: CraneParser

    init {
        val text = input.readResourceAsText().lines().withIndex()
        val delimiterIndex = text.first { it.value.isBlank() }.index

        val splitText = text
            .groupBy { indexedValue ->
                indexedValue.index.let {
                    when {
                        it < delimiterIndex -> 0
                        it > delimiterIndex -> 1
                        else -> 3
                    }
                }
            }.mapValues { entry ->
                entry.value.map { it.value }
            }

        shipParser = ShipParser(splitText[0]!!)
        craneParser = CraneParser(splitText[1]!!)
    }

    val ship by lazy { shipParser.parse() }
//    fun parseShip() = shipParser.parse()

    private class ShipParser(val input: List<String>) {

        fun parse() = Ship(
            input
                .map { identifyColumns(it) }
                .map { it.asSequence() }
                .reduce { a, b -> a + b }
                .groupBy({ it.key }, { it.value })
                .mapValues { entry -> CrateStack(entry.value) }
        )

        companion object {
            private const val IDX_SUPPLY_VALUE = 1
            private const val COLUMN_COUNT = 4
        }

        private fun identifyColumns(line: String): Map<Int, Crate> {
            return line.withIndex().mapNotNull {
                when (it.index % COLUMN_COUNT) {
                    IDX_SUPPLY_VALUE -> return@mapNotNull Crate(it.value)
                    else -> return@mapNotNull null
                }
            }.withIndex()
                .associate { it.index + 1 to it.value }
                .filter { it.value.value.isLetter() }
        }
    }

    val instructions by lazy { craneParser.parse() }

    private class CraneParser(val input: List<String>) {

        fun parse(): CraneInstructions {
            val moves = input
                .map {
                    it.removeUnusedText()
                }.map {
                    it.split(",")
                }.map { list ->
                    list.map { it.toInt() }
                }.map {
                    CraneMove(it[0], it[1], it[2])
                }
            return CraneInstructions(moves)
        }

        private fun String.removeUnusedText() = this
            .replace("move ", "")
            .replace(" from ", ",")
            .replace(" to ", ",")
    }

}