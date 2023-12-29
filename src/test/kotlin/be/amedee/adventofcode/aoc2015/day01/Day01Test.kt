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
    private val input = Day01().readFile()

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

    private val findBasementTestData = listOf(
        "" to 0,
        ")" to 1,
        " )" to 2,
        "()" to 0,
        "())" to 3,
        "*" to 0,
        "(())" to 0,
        "()()" to 0,
        "(((" to 0,
        "(()(()(" to 0,
        "))(((((" to 1,
        "())" to 3,
        "))(" to 1,
        ")))" to 1,
        ")())())" to 1,
        "()(()))" to 7,
        "()()(()(()" to 0,
        "(()(()))))" to 9,
        "()()()()()()(" to 0,
        "(()(()()()(())))" to 0,
        "(()()()()(()(()(()))())())" to 0,
        "(((())))((((()))))" to 0,
        ")()()()()()()()()()" to 1,
        "()(()(()))()())(((()))" to 15,
        null to 0,
        input to 1797,
    )

    @Test
    fun `one move in elevator`() =
        oneMoveTestData.forEach { (instruction, direction) ->
            move()(instruction.toString()) shouldBe direction
        }

    @Test
    fun `several moves in elevator`() =
        severalMovesTestData.forEach { (instructions, floor) ->
            followInstructions()(instructions.toString()) shouldBe floor
        }

    @Test
    fun `find the basement`() =
        findBasementTestData.forEach { (instructions, basement) ->
            findBasementPosition(instructions.toString()) shouldBe basement
    }
}
