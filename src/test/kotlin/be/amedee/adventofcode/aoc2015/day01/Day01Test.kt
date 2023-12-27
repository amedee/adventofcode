package be.amedee.adventofcode.aoc2015.day01

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * Solution in Bash:
 * echo $(($(grep --only-matching "(" input | wc -l) - $(grep --only-matching ")" input | wc -l)))
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class Day01Test {
    private val input = Day01Test::class.java.getResource("input")?.readText()

    private val oneMoveTestData =
        listOf(
            "(" to 1,
            ")" to -1,
            "" to 0,
            "*" to 0,
            "(())" to 0,
            "()()" to 0,
            "(((" to 0,
            "(()(()(" to 0,
            "))(((((" to 0,
            "())" to 0,
            "))(" to 0,
            ")))" to 0,
            ")())())" to 0,
            null to 0,
        )

    private val severalMovesTestData =
        listOf(
            "(" to 1,
            ")" to -1,
            "" to 0,
            "*" to 0,
            "(())" to 0,
            "()()" to 0,
            "(((" to 3,
            "(()(()(" to 3,
            "))(((((" to 3,
            "())" to -1,
            "))(" to -1,
            ")))" to -3,
            ")())())" to -3,
            null to 0,
            input to 280,
        )

    @Test
    fun `one move in elevator`() =
        oneMoveTestData.forEach { (instruction, direction) ->
            move(instruction.toString()) shouldBe direction
        }

    @Test
    fun `several moves in elevator`() =
        severalMovesTestData.forEach { (instructions, floor) ->
            followInstructions(instructions.toString()) shouldBe floor
        }
}
