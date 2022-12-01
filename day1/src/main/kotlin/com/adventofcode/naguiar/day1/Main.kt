package com.adventofcode.naguiar.day1

import com.adventofcode.naguiar.day1.service.ElfService

fun main() {
    val elfService = ElfService("input.txt")

    val elf = elfService.mostCaloriesElf()
    println("Elf with most calories -> [${elf.id}] , snacksCalories [${elf.snacksCalories()}] ")
    println(elf)

    val top3 = elfService.top3Calories()

    println(
        """
            Top 3
                Ids -> [ ${top3.map { it.id }.joinToString(", ")} ]
                Calories by Elf -> [ ${top3.map { it.id to it.snacksCalories() }.joinToString(", ")} ]
                Total -> [ ${top3.sumOf { it.snacksCalories() }} ]
        """
    )
}

