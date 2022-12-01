package com.adventofcode.naguiar.day1.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Condition
import org.junit.Test
import kotlin.test.assertFailsWith

class ElfServiceTest {

    @Test
    fun `given an invalid path, should throw IllegalArgumentException`() {
        assertFailsWith(IllegalArgumentException::class) { ElfService("now.txt") }
    }

    @Test
    fun `given a single elf input, should return the elf with his 3 entries and calculate the total correctly`() {
        val elf = ElfService("single-elf-input.txt").elfs.firstOrNull()

        assertThat(elf).isNotNull
        assertThat(elf?.snacks).hasSize(3)
        assertThat(elf?.snacksCalories()).isEqualTo(6000L)
    }

    @Test
    fun `given a two elf input, should return two elfs and their respective calories expected`() {
        val elfs = ElfService("two-elfs-input.txt").elfs

        val elf1 = elfs.firstOrNull()
        val elf2 = elfs.lastOrNull()

        assertThat(elf1).isNotNull
        assertThat(elf1?.snacks).hasSize(3)
        assertThat(elf1?.snacksCalories()).isEqualTo(6000L)

        assertThat(elf2).isNotNull
        assertThat(elf2?.snacks).hasSize(3)
        assertThat(elf2?.snacksCalories()).isEqualTo(6000L)
    }

    @Test
    fun `given the sample input, should provide 4th Elf as the result`() {
        val elf = ElfService("input.txt").mostCaloriesElf()

        assertThat(elf).has(condition { it.id == 4L })
        assertThat(elf.snacksCalories()).isEqualTo(24000L)
    }

    @Test
    fun `given the sample input, the top3 elfs shoud match the expected output`() {
        val top3 = ElfService("input.txt").top3Calories()

        val idAndTotal = top3.map { it.id to it.snacksCalories() }

        assertThat(top3).has(condition { list ->
            list.sumOf { it.snacksCalories() } == 45000L
        })
        assertThat(idAndTotal).containsExactly(
            1L to 100L
        )
    }

}

inline fun <T> condition(crossinline predicate: (T) -> Boolean) =
    Condition<T>({ predicate(it) }, "")
