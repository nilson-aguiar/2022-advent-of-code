package com.adventofcode.naguiar.day3.service

import com.adventofcode.naguiar.day3.domain.RuckSack
import com.adventofcode.naguiar.readAsResourceStream

class RucksackParser(
    private val input: String
) {
    fun parseRucksacks(): List<RuckSack> {
        return input.readAsResourceStream().lines()
            .map {
                RuckSack(it)
            }.toList()
    }
}