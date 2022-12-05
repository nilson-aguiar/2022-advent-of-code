package com.adventofcode.naguiar.day5

import com.adventofcode.naguiar.day5.service.CraneMoveMode
import com.adventofcode.naguiar.day5.service.CraneService
import com.adventofcode.naguiar.day5.service.InstructionsParser

fun main() {
    println("Hello, World")

    val ship = InstructionsParser("input.txt")
        .let { parser ->
            CraneService().executeInstructions(parser.ship, parser.instructions, CraneMoveMode.INDIVIDUALLY)
        }

    val topResult = ship.stacks.mapValues { (_, value) ->
        value.crates.first()
    }.toSortedMap().map { it.value.value }
    println(
        topResult.joinToString("")
    )

}


