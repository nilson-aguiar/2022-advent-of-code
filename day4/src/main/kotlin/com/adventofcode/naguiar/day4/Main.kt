package com.adventofcode.naguiar.day4

import com.adventofcode.naguiar.day4.service.CampParser
import com.adventofcode.naguiar.day4.service.CampService

fun main() {
    val campService = CampService(CampParser("input.txt"))

    println(campService.fullyContainedPairsCount())
    println(campService.overlappingPairsCount())
}


