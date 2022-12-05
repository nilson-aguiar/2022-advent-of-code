package com.adventofcode.naguiar.day5.service

import com.adventofcode.naguiar.day5.domain.CraneInstructions
import com.adventofcode.naguiar.day5.domain.CrateStack
import com.adventofcode.naguiar.day5.domain.Ship

enum class CraneMoveMode {
    INDIVIDUALLY, GROUPED
}


class CraneService {

    fun executeInstructions(ship: Ship, instructions: CraneInstructions, mode: CraneMoveMode): Ship {
        val stacks = ship.stacks.toMutableMap()
        instructions.moves.forEach {
            val from = stacks[it.from]!!
            val move = CrateStack(from.crates.take(it.amount))

            stacks[it.from] = from - move
            stacks[it.to] = stacks[it.to]!! + when (mode) {
                CraneMoveMode.INDIVIDUALLY -> move.reversed()
                CraneMoveMode.GROUPED -> move
            }
        }

        return Ship(stacks.toMap())
    }

}