package be.amedee.adventofcode.aoc2015.day01

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Single move to the next floor, up or down.
 *
 * @param instruction a single elevator instruction
 * @return do we go up or down
 */
fun move(): (String) -> Int = {
    when (it) {
        "(" -> 1
        ")" -> -1
        else -> 0
    }
}

/**
 * The entire list of elevator instructions.
 *
 * @param instructions the list of elevator instructions, Lisp-style
 * @return at which floor Santa ends up
 */
fun followInstructions(): (String) -> Int = { instructionList ->
    instructionList
        .toList()
        .sumOf { move()(it.toString()) }
}

/**
 * Find the position of the first character that causes Santa to enter the
 * basement (floor -1). The first character in the instructions has
 * position 1, the second character has position 2, and so on.
 */
fun findBasementPosition(instructions: String): Int {
    var floor = 0
    return instructions.indexOfFirst {
        when (it) {
            '(' -> floor++
            ')' -> floor--
        }
        floor == -1
    } + 1
}

class Day01 {
    fun readFile(): String {
        val packageName = javaClass.`package`.name.replace(".", "/")
        val fileName = "input"
        val filePath = "/$packageName/$fileName"
        val inputStream = javaClass.getResourceAsStream(filePath)
        return BufferedReader(InputStreamReader(inputStream!!)).use { it.readText().trim() }
    }

}

/**
 * Santa is trying to deliver presents in a large apartment building,
 * but he can't find the right floor - the directions he got are a little confusing.
 */
fun main() {
    val puzzleInput = Day01().readFile()

    val endFloor = followInstructions()(puzzleInput)
    println("Santa ends up on floor $endFloor.")

    val basementPosition = findBasementPosition(puzzleInput)
    if (basementPosition == 0) {
        println("Santa never enters the basement.")
    } else {
        println("Santa enters the basement at character position $basementPosition.")
    }
}
