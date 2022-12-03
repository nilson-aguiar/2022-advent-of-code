package com.adventofcode.naguiar.day3.extension

class OrderedIterator<T>(private val iterator: Iterator<T>) {

    var current: T? = null

    operator fun next(): T {
        current = iterator.next()
        return current!!
    }

    /**
     * Returns `true` if the iteration has more elements.
     */
    operator fun hasNext(): Boolean = iterator.hasNext()

}