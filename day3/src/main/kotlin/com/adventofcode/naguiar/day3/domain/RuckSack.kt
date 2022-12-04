package com.adventofcode.naguiar.day3.domain

import com.adventofcode.naguiar.OrderedIterator

class RuckSack(items: String) {
    private val firstCompartment: Compartment
    private val secondCompartment: Compartment

    init {
        firstCompartment = Compartment(items.substring(0, items.length / 2))
        secondCompartment = Compartment((items.substring(items.length / 2, items.length)))
    }

    fun singleCompartment() = firstCompartment + secondCompartment

    fun findSharedItems(): List<Char> = listOf(firstCompartment, secondCompartment).findSharedItems()
}

fun List<Compartment>.findSharedItems(): List<Char> {
    assert(this.size > 1) { "To look for shared items the list has to contain at least two items" }

    fun findSharedItemsRecursively(valueToFind: Int, iterators: Iterator<OrderedIterator<Int>>): Boolean {
        val currentIterator = iterators.next()

        var currentIteratorValue = currentIterator.current ?: currentIterator.next()

        while (true) {
            if (valueToFind == currentIteratorValue) {
                iterators
                    .takeIf { it.hasNext() }
                    ?.let { return findSharedItemsRecursively(valueToFind, it) } ?: return true
            }

            if (valueToFind < currentIteratorValue) {
                return false
            }

            currentIteratorValue = currentIterator
                .takeIf { currentIterator.hasNext() }
                ?.next() ?: break
        }

        return false
    }

    val firstList = this[0].items
    val otherLists = this.subList(1, this.size).map { OrderedIterator(it.items.iterator()) }.toList()

    return firstList.filter {
        findSharedItemsRecursively(it, otherLists.iterator())
    }.map { it.toChar() }.toList()
}

