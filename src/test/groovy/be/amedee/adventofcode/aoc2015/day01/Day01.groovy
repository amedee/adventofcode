package be.amedee.adventofcode.aoc2015.day01

import spock.lang.Specification
import spock.lang.Unroll

class Day01 extends Specification {

    String input = getClass().getResource("input").getText("UTF-8")

    int move(String instruction) {
        int direction
        switch (instruction) {
            case "(":
                direction = 1
                break
            case ")":
                direction = -1
                break
            default:
                direction = 0
                break
        }
        direction
    }

    def "Day 1 - Part 1"() {
        println(followInstructions(input))
        expect:
        true

    }

    @Unroll
    def "one move in elevator"(String instruction, int direction) {
        expect:
        move(instruction) == direction

        where:
        instruction | direction
        "("         | 1
        ")"         | -1
        ""          | 0
        "*"         | 0
        "(())"      | 0
        "()()"      | 0
        "((("       | 0
        "(()(()("   | 0
        "))((((("   | 0
        "())"       | 0
        "))("       | 0
        ")))"       | 0
        ")())())"   | 0
    }

    @Unroll
    def "several moves in elevator"(String instructions, int floor) {
        expect:
        followInstructions(instructions) == floor

        where:
        instructions | floor
        "("          | 1
        ")"          | -1
        ""           | 0
        "*"          | 0
        "(())"       | 0
        "()()"       | 0
        "((("        | 3
        "(()(()("    | 3
        "))((((("    | 3
        "())"        | -1
        "))("        | -1
        ")))"        | -3
        ")())())"    | -3
    }

    int followInstructions(String instructions) {
        int floor
        instructions.each {
            movement -> floor += move(movement)
        }
        floor
    }
}
