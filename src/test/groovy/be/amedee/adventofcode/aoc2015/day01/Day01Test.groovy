package be.amedee.adventofcode.aoc2015.day01

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static be.amedee.adventofcode.aoc2015.day01.Day01.followInstructions
import static be.amedee.adventofcode.aoc2015.day01.Day01.move

/**
 * Solution in Bash:
 * echo $(($(grep --only-matching "(" input | wc -l) - $(grep --only-matching ")" input | wc -l)))
 */
class Day01Test extends Specification {

    @Shared
    private final input = getClass()
            .getResource('input')
            .getText('UTF-8')

    @Unroll
    private 'one move in elevator'(String instruction, int direction) {
        expect:
        move(instruction) == direction

        where:
        instruction | direction
        '('         | 1
        ')'         | -1
        ''          | 0
        '*'         | 0
        '(())'      | 0
        '()()'      | 0
        '((('       | 0
        '(()(()('   | 0
        '))((((('   | 0
        '())'       | 0
        '))('       | 0
        ')))'       | 0
        ')())())'   | 0
        null        | 0
    }

    @Unroll
    private 'several moves in elevator'(String instructions, int floor) {
        expect:
        followInstructions(instructions) == floor

        where:
        instructions | floor
        '('          | 1
        ')'          | -1
        ''           | 0
        '*'          | 0
        '(())'       | 0
        '()()'       | 0
        '((('        | 3
        '(()(()('    | 3
        '))((((('    | 3
        '())'        | -1
        '))('        | -1
        ')))'        | -3
        ')())())'    | -3
        null         | 0
        input        | 280
    }

}
