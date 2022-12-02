package com.adventofcode.naguiar

fun String.readAsResourceStream() =
    {}::class.java.getResourceAsStream("/$this")?.bufferedReader()
        ?: throw IllegalArgumentException("Invalid Path [$this]")