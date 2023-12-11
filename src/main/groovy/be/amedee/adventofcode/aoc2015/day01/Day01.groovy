package be.amedee.adventofcode.aoc2015.day01

class Day01 {

    static move = { String instruction ->
        switch (instruction) {
            case "(":
                return 1
            case ")":
                return -1
            default:
                return 0
        }
    }

    static followInstructions = { String instructions ->
        (instructions ?: '')
                .toList()
                .stream()
                .mapToInt(move)
                .sum()
    }

}