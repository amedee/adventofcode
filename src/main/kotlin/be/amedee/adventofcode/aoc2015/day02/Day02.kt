package be.amedee.adventofcode.aoc2015.day02

import java.io.BufferedReader
import java.io.InputStreamReader

fun calculateWrappingPaperOrder(presentDimensions: List<Triple<Int, Int, Int>>): Int {
    var totalSquareFeet = 0

    for ((l, w, h) in presentDimensions) {
        val surfaceArea = 2 * (l * w + w * h + h * l)
        val slack = minOf(l * w, w * h, h * l)
        totalSquareFeet += surfaceArea + slack
    }

    return totalSquareFeet
}

fun readPresentDimensionsFromFile(fileName: String): List<Triple<Int, Int, Int>> {
    val inputStream = {}.javaClass.classLoader.getResourceAsStream(fileName)
    val presentDimensions = mutableListOf<Triple<Int, Int, Int>>()

    if (inputStream != null) {
        BufferedReader(InputStreamReader(inputStream)).useLines { lines ->
            lines.forEach { line ->
                val dimensions = line.split("x").map { it.toInt() }
                if (dimensions.size == 3) {
                    presentDimensions.add(Triple(dimensions[0], dimensions[1], dimensions[2]))
                }
            }
        }
    }

    return presentDimensions
}

fun main() {
    // Specify the file path
    val filePath = "be/amedee/adventofcode/aoc2015/day02/input"

    // Read present dimensions from the file
    val presentDimensions = readPresentDimensionsFromFile(filePath)

    // Calculate the total square feet of wrapping paper needed
    val totalSquareFeet = calculateWrappingPaperOrder(presentDimensions)

    // Print the result
    println("Elves should order $totalSquareFeet square feet of wrapping paper.")
}
