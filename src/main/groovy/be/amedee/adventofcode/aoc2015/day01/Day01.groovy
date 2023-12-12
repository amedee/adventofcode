package be.amedee.adventofcode.aoc2015.day01

import java.util.function.ToIntFunction

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
    static Closure<Integer> move = { String instruction ->
        switch (instruction) {
            case '(':
                return 1
            case ')':
                return -1
            default:
                return 0
        }
    }

    /**
     * The entire list of elevator instructions.
     *
     * @param instructions the list of elevator instructions, Lisp-style
     * @return at which floor Santa ends up
     */
    static Closure<Integer> followInstructions = { String instructions ->
        (instructions ?: '')
                .toList()
                .stream()
                .mapToInt(move as ToIntFunction<? super String>)
                .sum()
    }

}
