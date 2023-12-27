package be.amedee.adventofcode.aoc2015.day01

fun readStringFromResource(filePath: String) = {}.javaClass.classLoader.getResource(filePath)?.readText()

fun findFloorAndBasementPosition(instructions: String): Pair<Int, Int> {
    var floor = 0
    var basementPosition = -1

    for ((index, char) in instructions.withIndex()) {
        when (char) {
            '(' -> floor++
            ')' -> {
                floor--
                if (floor == -1 && basementPosition == -1) {
                    basementPosition = index + 1 // Adjust index to start from 1
                }
            }
        }
    }

    return Pair(floor, basementPosition)
}

fun main() {
    // Specify the file path
    val filePath = "be/amedee/adventofcode/aoc2015/day01/input"

    val puzzleInput: String = readStringFromResource(filePath)!!

    val (floor, basementPosition) = findFloorAndBasementPosition(puzzleInput)

    println("Santa ends up on floor $floor.")

    if (basementPosition != -1) {
        println("Santa enters the basement at character position $basementPosition.")
    } else {
        println("Santa never enters the basement.")
    }
}
