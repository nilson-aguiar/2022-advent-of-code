package com.adventofcode.naguiar.day4.service

import com.adventofcode.naguiar.day4.domain.Camp
import com.adventofcode.naguiar.day4.domain.Elf
import com.adventofcode.naguiar.readAsResourceStream

class CampParser(private val input: String) {

    fun camps(): List<Camp> = input.readAsResourceStream().lines()
        .map { camp ->
            camp.split(",")
        }
        .map { elfRange ->
            val elfList = elfRange.map {
                val range = it.split("-")

                assert(range.size == 2) { "Range should have only two value " }

                Elf(range.first(), range.last())
            }

            Camp(elfList)
        }.toList()

}