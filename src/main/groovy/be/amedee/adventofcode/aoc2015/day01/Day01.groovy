package be.amedee.adventofcode.aoc2015.day01

import java.util.function.ToIntFunction

class Day01 {

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

    static Closure<Integer> followInstructions = { String instructions ->
        (instructions ?: '')
                .toList()
                .stream()
                .mapToInt(move as ToIntFunction<? super String>)
                .sum()
    }

}
