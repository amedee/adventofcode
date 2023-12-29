package be.amedee.adventofcode.aoc2015.day02

import java.io.BufferedReader
import java.io.InputStreamReader

fun getSurfaceArea(
    length: Int,
    width: Int,
    height: Int,
) = 2 * ((length * width) + (width * height) + (height * length))

fun getSlack(
    length: Int,
    width: Int,
    height: Int,
) = minOf(length * width, width * height, height * length)

fun calculateWrappingPaperOrder(presentDimensions: List<Triple<Int, Int, Int>>): Int {
    var totalSquareFeet = 0

    presentDimensions.forEach { (l, w, h) ->
        val surfaceArea = getSurfaceArea(l, w, h)
        val slack = getSlack(l, w, h)
        totalSquareFeet += surfaceArea + slack
    }

    return totalSquareFeet
}

class Day02 {
    fun readPresentDimensionsFromFile(fileName: String): List<Triple<Int, Int, Int>> {
        val packageName = javaClass.`package`.name.replace(".", "/")
        val filePath = "/$packageName/$fileName"
        val inputStream = javaClass.getResourceAsStream(filePath)

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
}

fun main() {
    val inputFile = "input"
    val presentDimensions = Day02().readPresentDimensionsFromFile(inputFile)

    val totalSquareFeet = calculateWrappingPaperOrder(presentDimensions)
    println("Elves should order $totalSquareFeet square feet of wrapping paper.")
}
