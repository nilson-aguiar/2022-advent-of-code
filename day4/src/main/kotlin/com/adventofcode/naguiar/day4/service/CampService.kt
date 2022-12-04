package com.adventofcode.naguiar.day4.service

import com.adventofcode.naguiar.containsOrContainedIn
import com.adventofcode.naguiar.overLapsOrOverlappedIn

class CampService(
    campParser: CampParser
) {

    private val camps = campParser.camps()

    fun fullyContainedPairsCount(): Int {
        val ranges = getRanges()

        return ranges.entries.count {
            it.value.containsOrContainedIn()
        }
    }

    fun overlappingPairsCount(): Int {
        val ranges = getRanges()

        return ranges.entries.count {
            it.value.overLapsOrOverlappedIn()
        }
    }

    private fun getRanges() = camps.associateWith { camp ->
        val ranges = camp.elfList.map { elf ->
            elf.sectionsAsIntRage()
        }

        assert(ranges.size == 2) { "Each camp should contain job for 2 elfs only" }

        Pair(ranges.first(), ranges.last())
    }

}

fun Pair<IntRange, IntRange>.containsOrContainedIn(): Boolean {
    return first.containsOrContainedIn(second)
}

fun Pair<IntRange, IntRange>.overLapsOrOverlappedIn(): Boolean {
    return first.overLapsOrOverlappedIn(second)
}