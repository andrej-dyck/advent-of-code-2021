package ad.kata.aoc2021.day08

fun main() {
    val troubleshooting = troubleShootingFromInput("day08.input")

    println("-- Day 8: Seven Segment Search --")
    /* part 2 */
    val sumOfOutputValues = troubleshooting.deducedOutputValues().filterNotNull().sum()
    println("Sum of output values: $sumOfOutputValues")
}