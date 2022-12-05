package com.adventofcode.naguiar

fun String.readResourceAsText() =
    {}::class.java.getResource("/$this")?.readText()
        ?: throw IllegalArgumentException("Invalid Path [$this]")

fun String.readResourceAsStream() =
    {}::class.java.getResourceAsStream("/$this")?.bufferedReader()
        ?: throw IllegalArgumentException("Invalid Path [$this]")

fun <T> Set<T>.noRepeatedItemsPermutations(): Set<List<T>> {
    if (this.isEmpty()) return emptySet()

    fun <T> permutations(list: List<T>): Set<List<T>> {
        if (list.isEmpty()) return setOf(emptyList())

        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in list.indices) {
            permutations(list - list[i]).forEach { item ->
                result.add(item + list[i])
            }
        }
        return result
    }

    return permutations(this.toList())
}

fun ClosedRange<Int>.containsOrContainedIn(other: ClosedRange<Int>): Boolean {
    return listOf(
        Pair(this, other), Pair(other, this)
    ).map { (first, second) ->
        first.start <= second.start && second.endInclusive <= first.endInclusive
    }.reduce { a, b ->
        a || b
    }
}

fun ClosedRange<Int>.overLapsOrOverlappedIn(other: ClosedRange<Int>): Boolean {
    return listOf(
        Pair(this, other), Pair(other, this)
    ).map { (first, second) ->
        first.contains(second.start) || first.contains(second.endInclusive)
    }.reduce { a, b ->
        a || b
    }
}
