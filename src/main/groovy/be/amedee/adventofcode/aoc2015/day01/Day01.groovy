package be.amedee.adventofcode.aoc2015.day01

class Day01 {

    static int move(String instruction) {
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

    static int followInstructions(String instructions) {
        int floor
        instructions.each {
            movement -> floor += move(movement)
        }
        floor
    }

}