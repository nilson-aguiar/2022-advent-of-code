package com.adventofcode.naguiar.day5.domain

data class CrateStack(val crates: List<Crate>) {

    fun reversed() = CrateStack(crates.reversed())

    operator fun plus(other: CrateStack) =
        CrateStack(other.crates + this.crates)

    operator fun minus(other: CrateStack): CrateStack {
        val mutableCrates = crates.toMutableList()

        other.crates.forEach {
            mutableCrates.remove(it)
        }

        return CrateStack(mutableCrates.toList())
    }

}