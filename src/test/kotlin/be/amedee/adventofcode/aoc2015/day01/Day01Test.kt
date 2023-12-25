package be.amedee.adventofcode.aoc2015.day01

import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 * Solution in Bash:
 * echo $(($(grep --only-matching "(" input | wc -l) - $(grep --only-matching ")" input | wc -l)))
 */
class Day01Test {

    private val input = Day01Test::class.java.getResource("input")?.readText()

    @Test
    fun `one move in elevator`() = forAll(
        row("(", 1),
        row(")", -1),
        row("", 0),
        row("*", 0),
        row("(())", 0),
        row("()()", 0),
        row("(((", 0),
        row("(()(()(", 0),
        row("))(((((", 0),
        row("())", 0),
        row("))(", 0),
        row(")))", 0),
        row(")())())", 0),
        row(null, 0)
    ) { instruction, direction ->
        Day01().move(instruction.toString()) shouldBe direction
    }

    @Test
    fun `several moves in elevator`() = forAll(
        row("(", 1),
        row(")", -1),
        row("", 0),
        row("*", 0),
        row("(())", 0),
        row("()()", 0),
        row("(((", 3),
        row("(()(()(", 3),
        row("))(((((", 3),
        row("())", -1),
        row("))(", -1),
        row(")))", -3),
        row(")())())", -3),
        row(null, 0),
        row(input, 280)
    ) { instructions, floor ->
        Day01().followInstructions(instructions.toString()) shouldBe floor
    }
}
