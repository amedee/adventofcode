package be.amedee.adventofcode.aoc2015.day01

/**
 * Santa is trying to deliver presents in a large apartment building,
 * but he can't find the right floor - the directions he got are a little confusing.
 */
class Day01 {

    /**
     * Single move to the next floor, up or down.
     *
     * @param instruction a single elevator instruction
     * @return do we go up or down
     */
    val move: (String) -> Int = {
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
    val followInstructions: (String) -> Int = { instructionList ->
        instructionList
            .toList()
            .sumOf { move(it.toString()) }
    }

    fun main() {
        val filePath = "be/amedee/adventofcode/aoc2015/day01/input"
        val puzzleInput: String = readStringFromResource(filePath)!!

        val endFloor = followInstructions(puzzleInput)
        println("Santa ends up on floor $endFloor.")
    }
}
