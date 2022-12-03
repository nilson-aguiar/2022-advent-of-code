package com.adventofcode.naguiar.day3

import com.adventofcode.naguiar.day3.domain.RuckSack
import com.adventofcode.naguiar.day3.domain.findSharedItems
import com.adventofcode.naguiar.day3.service.PriorityCalculator
import com.adventofcode.naguiar.day3.service.RucksackParser
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    measureTime {
        val ruckSacks = RucksackParser("input.txt").parseRucksacks()
        println(ruckSacks.sumPriorities())

        println(ruckSacks.findBadges(3).sumCharAsPriorities())
    }.also {
        println("Executed in $it")
    }
}

fun List<RuckSack>.sumPriorities(): Int {
    return this.map {
        it.findSharedItems()
    }.reduce { acc, chars ->
        acc + chars
    }.sumCharAsPriorities()
}

fun List<Char>.sumCharAsPriorities(): Int = this.sumOf { PriorityCalculator.INSTANCE.priority(it) }

fun List<RuckSack>.findBadges(groupSize: Int): List<Char> {
    assert(this.size % groupSize == 0) { "Has to be divisible by $groupSize" }

    fun List<RuckSack>.findSharedItems(): List<Char> {
        assert(this.size == groupSize) { "Group has wrong size" }

        return this.map {
            it.singleCompartment()
        }.findSharedItems()
    }

    return this.withIndex()
        .groupBy(
            keySelector = {
                it.index / groupSize
            }, valueTransform = {
                it.value
            })
        .mapValues {
            it.value.findSharedItems()
        }.map { it.value }.reduce { acc, chars -> acc+chars }
}

