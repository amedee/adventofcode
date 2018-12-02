package be.amedee.adventofcode.aoc2015.day01

import spock.lang.Specification
import spock.lang.Unroll

class Day01 extends Specification {

    String input = getClass().getResource("input").getText("UTF-8")

    def "Day 1 - Part 1"() {
        println(input)
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

}
