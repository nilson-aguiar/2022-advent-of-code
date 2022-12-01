package com.adventofcode.naguiar.day1.service

import com.adventofcode.naguiar.day1.domain.Elf
import com.adventofcode.naguiar.day1.domain.Snack

class ElfService(inputPath: String) {

    val elfs = parseElfSnacks(inputPath)

    fun mostCaloriesElf(): Elf =
         elfs.maxByOrNull { it.snacksCalories() } ?: throw IllegalArgumentException("No elf available")

    fun top3Calories(): List<Elf> =
        elfs.sortedByDescending { it.snacksCalories() }.take(3)

    private fun parseElfSnacks(inputPath: String): List<Elf> {
        var currentId = 1L
        return reader(inputPath).lineSequence()
            .mapNotNull {
                if (it.isBlank()) {
                    currentId++
                    return@mapNotNull null
                }

                currentId to it
            }
            .groupBy { it.first }
            .map { (key, items) ->
                Elf(key, items.map { Snack(it.second.toLong()) }.toList())
            }
    }

    private fun reader(inputPath: String) =
        {}::class.java.getResourceAsStream("/$inputPath")?.bufferedReader()
            ?: throw IllegalArgumentException("Invalid Path [$inputPath]")

}